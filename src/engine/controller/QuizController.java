package engine.controller;

import engine.model.Question;
import engine.model.User;
import engine.model.dto.AnswerRequest;
import engine.model.dto.FeedbackResponse;
import engine.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('USER')")
@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

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

    @GetMapping
    public ResponseEntity<List<Question>> findAllQuizzes() {
        List<Question> quizzes = quizService.findAllQuizzes();
        return ResponseEntity.ok().body(quizzes);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<FeedbackResponse> solveQuiz(@PathVariable long id, @RequestBody(required = false) AnswerRequest answer,
                                                      @AuthenticationPrincipal User user) {
        FeedbackResponse feedbackResponse = quizService.feedbackByQuizId(id, answer);
        if (feedbackResponse.isSuccess()) {
//            taskService.addCompletedTask(id, user);
        }
        return ResponseEntity.ok().body(feedbackResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable long id, @AuthenticationPrincipal User user) {
        quizService.deleteQuizById(id, user);
        return ResponseEntity.status(204).build();
    }
}
