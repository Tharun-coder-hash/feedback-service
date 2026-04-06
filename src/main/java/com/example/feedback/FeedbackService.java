package com.example.feedback;

import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public void saveFeedback(FeedbackRequest request) {
        Feedback feedback = new Feedback();
        feedback.setUserId(request.getUserId());
        feedback.setContent(request.getContent());
        feedbackRepository.save(feedback);
    }
}
