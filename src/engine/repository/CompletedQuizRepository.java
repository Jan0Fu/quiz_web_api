package engine.repository;

import engine.model.CompletedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompletedQuizRepository extends JpaRepository<CompletedQuiz, Long>, PagingAndSortingRepository<CompletedQuiz, Long> {
    Page<CompletedQuiz> findCompletedQuizByUserId(long id, Pageable pageable);
}
