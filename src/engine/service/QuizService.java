package engine.service;

import engine.model.Question;
import engine.model.dto.AnswerRequest;
import engine.model.dto.QuestionDto;
import engine.model.dto.QuizResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {

    List<Question> getQuizzes();

    QuizResponse postAnswer(long id, AnswerRequest answer);

    ResponseEntity<Question> getQuestion(long id);

    Question postQuiz(QuestionDto body);
}
