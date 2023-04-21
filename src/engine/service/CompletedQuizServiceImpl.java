package engine.service;

import engine.model.CompletedQuiz;
import engine.model.Question;
import engine.model.User;
import engine.model.dto.CompletedQuizResponse;
import engine.repository.CompletedQuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompletedQuizServiceImpl implements CompletedQuizService {

    private final CompletedQuizRepository completedQuizRepository;
    private final QuizService quizService;

    @Override
    public void addCompletedQuiz(long id, User user) {
        Question quiz = quizService.getQuizById(id).get();
        completedQuizRepository.save(new CompletedQuiz(quiz.getId(), LocalDateTime.now(), user));
    }

    @Override
    public CompletedQuizResponse findAllCompletedQuizzes(int page, User user) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("completedAt").descending());
        Page<CompletedQuiz> completedQuizzes = completedQuizRepository.findCompletedQuizByUserId(user.getId(), pageable);
        return CompletedQuizResponse.builder()
                .totalPages(completedQuizzes.getTotalPages())
                .totalElements(completedQuizzes.getTotalElements())
                .last(completedQuizzes.isLast())
                .first(completedQuizzes.isFirst())
                .empty(completedQuizzes.isEmpty())
                .content(completedQuizzes.getContent()).build();
    }
}
