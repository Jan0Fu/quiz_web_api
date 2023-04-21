package engine.model.dto;

import engine.model.CompletedQuiz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompletedQuizResponse {
    private int totalPages;
    private long totalElements;
    private boolean last;
    private boolean first;
    private boolean empty;
    private List<CompletedQuiz> content;
}
