package engine.service;

import engine.model.Question;
import engine.model.User;
import engine.model.dto.AnswerRequest;
import engine.model.dto.FeedbackResponse;
import engine.model.dto.QuizPageResponse;
import engine.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public FeedbackResponse getFeedback(int answer) {
        final FeedbackResponse feedbackResponse;
        if (answer == 2) {
            feedbackResponse = new FeedbackResponse(true, "Congratulations, you're right!");
        } else {
            feedbackResponse = new FeedbackResponse(false, "Wrong answer! Please, try again.");
        }
        return feedbackResponse;
    }

    @Override
    public Question addQuiz(Question quiz, User user) {
        quiz.setUser(user);
        return quizRepository.save(quiz);
    }

    @Override
    public Optional<Question> getQuizById(long id) {
        Optional<Question> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            return quiz;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public QuizPageResponse findAllQuizzes(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Question> quizzes = quizRepository.findAll(pageable);
        List<Question> content = quizzes.getContent();
        return QuizPageResponse.builder()
                .totalPages(quizzes.getTotalPages())
                .totalElements(quizzes.getTotalElements())
                .last(quizzes.isLast())
                .first(quizzes.isFirst())
                .sort(quizzes.getSort())
                .number(quizzes.getNumber())
                .numberOfElements(quizzes.getNumberOfElements())
                .size(quizzes.getSize())
                .empty(quizzes.isEmpty())
                .pageable(quizzes.getPageable())
                .content(content)
                .build();
    }

    @Override
    public FeedbackResponse feedbackByQuizId(long id, AnswerRequest answer) {
        final FeedbackResponse successfulFeedback = new FeedbackResponse(true, "Congratulations, you're right!");
        final FeedbackResponse failedFeedback = new FeedbackResponse(false, "Wrong answer! Please, try again.");
        List<Integer> quizAnswers;

        Optional<Question> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            quizAnswers = quiz.get().getAnswer();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (quizAnswers == null && answer == null) {
            return successfulFeedback;
        }

        if (quizAnswers == null) {
            if (answer.getAnswer().isEmpty()) {
                return successfulFeedback;
            } else {
                return failedFeedback;
            }
        }

        if (answer == null) {
            if (quizAnswers.isEmpty()) {
                return successfulFeedback;
            } else {
                return failedFeedback;
            }
        }
        if (quizAnswers.isEmpty() && answer.getAnswer().isEmpty()) {
            return successfulFeedback;
        }

        if (Arrays.equals(quizAnswers.stream().sorted().toArray(), answer.getAnswer().stream().sorted().toArray())) {
            return successfulFeedback;
        }
        return failedFeedback;
    }

    @Override
    public void deleteQuizById(long id, User user) {
        Optional<Question> quiz = quizRepository.findById(id);
        if (quiz.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else if (quiz.get().getUser().getId() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            quizRepository.deleteById(id);
        }
    }
}
