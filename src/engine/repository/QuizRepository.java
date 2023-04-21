package engine.repository;

import engine.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Question, Long>, PagingAndSortingRepository<Question, Long> {
}
