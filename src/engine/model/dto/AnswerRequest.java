package engine.model.dto;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerRequest {
    private Set<Integer> answer;
}
