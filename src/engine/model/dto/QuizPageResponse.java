package engine.model.dto;

import engine.model.Question;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuizPageResponse {
    private int totalPages;
    private long totalElements;
    private boolean last;
    private boolean first;
    private Sort sort;
    private int number;
    private int numberOfElements;
    private int size;
    private Pageable pageable;
    private boolean empty;
    private List<Question> content;
}
