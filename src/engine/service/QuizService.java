package engine.service;

import engine.model.Question;
import engine.model.User;
import engine.model.dto.AnswerRequest;
import engine.model.dto.FeedbackResponse;
import engine.model.dto.QuizPageResponse;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    FeedbackResponse getFeedback(int answer);

    Question addQuiz(Question quiz, User user);

    Optional<Question> getQuizById(long id);

    List<Question> findAllQuizzes();

    FeedbackResponse feedbackByQuizId(long id, AnswerRequest answer);

    void deleteQuizById(long id, User user);
}
