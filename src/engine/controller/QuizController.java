package engine.controller;

import engine.model.Question;
import engine.model.dto.AnswerRequest;
import engine.model.dto.QuestionDto;
import engine.model.dto.QuizResponse;
import engine.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/quizzes")
    public Question postQuiz(@Valid @RequestBody QuestionDto body) {
        return quizService.postQuiz(body);
    }

    @GetMapping("/quizzes")
    public List<Question> getQuizzes() {
        return quizService.getQuizzes();
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable long id) {
        return quizService.getQuestion(id);
    }

    @PostMapping("/quizzes/{id}/solve")
    public QuizResponse postAnswer(@PathVariable long id, @RequestBody AnswerRequest answer) {
        return quizService.postAnswer(id, answer);
    }
}
