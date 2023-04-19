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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public Question postQuiz(QuestionDto body) {
        Question quiz = new Question(body.getTitle(), body.getText(), body.getOptions(), body.getAnswer());
        return quizRepository.save(quiz);
    }

    @Override
    public List<Question> getQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public ResponseEntity<Question> getQuestion(long id) {
        Optional<Question> quiz = quizRepository.findById(id);
        return quiz.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public QuizResponse postAnswer(long id, AnswerRequest answer) {
        Optional<Question> quiz = quizRepository.findById(id);
        if (quiz.isPresent() && Arrays.equals(answer.getAnswer(), quiz.get().getAnswer())) {
            return new QuizResponse(true, "Congratulations, you're right!");
        } else {
            return new QuizResponse(false, "Wrong answer! Please, try again.");
        }
    }
}
