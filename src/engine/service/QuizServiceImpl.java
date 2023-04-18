package engine.service;

import engine.model.Question;
import engine.model.dto.AnswerRequest;
import engine.model.dto.QuestionDto;
import engine.model.dto.QuizResponse;
import engine.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public Question postQuiz(QuestionDto body) {
        return quizRepository.addQuiz(body);
    }

    @Override
    public List<Question> getQuizzes() {
        return quizRepository.getAllQuizzes();
    }

    @Override
    public ResponseEntity<Question> getQuestion(int id) {
        Question quiz = quizRepository.getQuiz(id);
        if (quiz == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(quiz);
        }
    }

    @Override
    public QuizResponse postAnswer(int id, AnswerRequest answer) {
        Question quiz = quizRepository.getQuiz(id);
        if (Arrays.equals(answer.getAnswer(), quiz.getAnswer())) {
            return new QuizResponse(true, "Congratulations, you're right!");
        } else {
            return new QuizResponse(false, "Wrong answer! Please, try again.");
        }
    }
}
