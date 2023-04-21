package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class CompletedQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long id;
    @JsonProperty(value = "id")
    private long quizId;
    @Column(name = "completion_date")
    private LocalDateTime completedAt;
    @ManyToOne(targetEntity = User.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    public CompletedQuiz(long quizId, LocalDateTime completedAt, User user) {
        this.quizId = quizId;
        this.completedAt = completedAt;
        this.user = user;
    }
}
