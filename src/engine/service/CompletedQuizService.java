package engine.service;

import engine.model.User;
import engine.model.dto.CompletedQuizResponse;

public interface CompletedQuizService {
    void addCompletedQuiz(long id, User user);
    CompletedQuizResponse findAllCompletedQuizzes(int page, User user);
}
