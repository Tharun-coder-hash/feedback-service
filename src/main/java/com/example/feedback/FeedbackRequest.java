package com.example.feedback;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FeedbackRequest {
    @NotBlank(message = "User identifier cannot be empty")
    private String userId;

    @NotBlank(message = "Feedback content cannot be empty")
    @Size(min = 10, max = 1000, message = "Feedback must be between 10 and 1000 characters")
    private String content;

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
