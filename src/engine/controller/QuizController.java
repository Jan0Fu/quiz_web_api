package engine.controller;

import engine.model.Question;
import engine.model.User;
import engine.model.dto.AnswerRequest;
import engine.model.dto.CompletedQuizResponse;
import engine.model.dto.FeedbackResponse;
import engine.model.dto.QuizPageResponse;
import engine.service.CompletedQuizService;
import engine.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('USER')")
@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;
    private final CompletedQuizService completedService;

    @PostMapping("/api/quiz")
    public ResponseEntity<FeedbackResponse> feedback(@RequestParam int answer) {
        FeedbackResponse feedback = quizService.getFeedback(answer);
        return ResponseEntity.ok().body(feedback);
    }

    @PostMapping
    public ResponseEntity<Question> addQuiz(@RequestBody @Valid Question quiz, @AuthenticationPrincipal User user) {
        Question savedQuiz = quizService.addQuiz(quiz, user);
        return ResponseEntity.ok().body(savedQuiz);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getQuizById(@PathVariable long id) {
        Optional<Question> quiz = quizService.getQuizById(id);
        return ResponseEntity.ok().body(quiz);
    }

    @GetMapping()
    public ResponseEntity<QuizPageResponse> findAllQuizzes(
            @AuthenticationPrincipal User user,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int pageSize) {
        QuizPageResponse quizzes = quizService.findAllQuizzes(page, pageSize);
        return ResponseEntity.ok().body(quizzes);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<FeedbackResponse> solveQuiz(@PathVariable long id, @RequestBody(required = false) AnswerRequest answer,
                                                      @AuthenticationPrincipal User user) {
        FeedbackResponse feedbackResponse = quizService.feedbackByQuizId(id, answer);
        if (feedbackResponse.isSuccess()) {
            completedService.addCompletedQuiz(id, user);
        }
        return ResponseEntity.ok().body(feedbackResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable long id, @AuthenticationPrincipal User user) {
        quizService.deleteQuizById(id, user);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/completed")
    public ResponseEntity<CompletedQuizResponse> findAllCompletedQuizzes(
            @RequestParam(defaultValue = "0", required = false) int page,
            @AuthenticationPrincipal User user) {
        CompletedQuizResponse completedQuizzes = completedService.findAllCompletedQuizzes(page, user);
        return ResponseEntity.ok().body(completedQuizzes);
    }
}
