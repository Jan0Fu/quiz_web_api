package engine.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class QuestionDto {
    @NotNull
    private String title;
    @NotNull
    private String text;
    @NotNull
    @Size(min = 2, message = "Must have at least 2 options")
    private String[] options;
    private int[] answer;
}
