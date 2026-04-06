package com.example.feedback;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<String> submitFeedback(@Valid @RequestBody FeedbackRequest request) {
        feedbackService.saveFeedback(request);
        return ResponseEntity.ok("Feedback recorded successfully.");
    }
}
