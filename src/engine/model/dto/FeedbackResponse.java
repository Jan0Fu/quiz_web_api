package engine.model.dto;

import lombok.Value;

@Value
public class FeedbackResponse {
    boolean success;
    String feedback;
}
