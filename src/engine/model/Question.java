package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class Question {

    private static int count;
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String text;
    @NotNull
    @Size(min = 2, message = "Must have at least 2 options")
    private String[] options;
    @JsonIgnore
    private int[] answer;

    public Question(String title, String text, String[] options, int[] answer) {
        count++;
        this.id = count;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer == null ? new int[]{} : answer;
    }
}
