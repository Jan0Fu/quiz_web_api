package engine.repository;

import engine.model.Question;
import engine.model.dto.QuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class QuizRepository {

    private final Map<Integer, Question> quizzes = new HashMap<>();

    public List<Question> getAllQuizzes() {
        return new ArrayList<>(quizzes.values());
    }

    public Question getQuiz(int id) {
        return quizzes.get(id);
    }

    public Question addQuiz(QuestionDto body) {
        Question question = new Question(body.getTitle(), body.getText(), body.getOptions(), body.getAnswer());
        quizzes.put(question.getId(), question);
        return question;
    }
}
