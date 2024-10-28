package com.SmartContracts.upc.smartcontract.service;

import com.SmartContracts.upc.smartcontract.model.Review;

import java.util.List;

public interface ReviewService {

    public abstract List<Review> getAllReviews();
    public abstract List<Review> getReviewsByUserId(Long userId);
    public abstract List<Review> getReviewsByServiceId(Long serviceId);
    public abstract Review getReviewById(Long id);
    public abstract Review createReview(Review review);
    public abstract Review updateReview(Review review);
    public abstract void deleteReview(Long id);
    public void validateReview(Review review);
}
