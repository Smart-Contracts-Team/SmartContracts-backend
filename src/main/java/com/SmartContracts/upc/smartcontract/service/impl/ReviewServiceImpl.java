package com.SmartContracts.upc.smartcontract.service.impl;

import com.SmartContracts.upc.exception.ValidationException;
import com.SmartContracts.upc.smartcontract.model.Review;
import com.SmartContracts.upc.smartcontract.repository.ReviewRepository;
import com.SmartContracts.upc.smartcontract.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByInfluencerId(Long influencerId) {
        return reviewRepository.findByInfluencerId(influencerId);
    }

    @Override
    public List<Review> getReviewsByServiceId(Long serviceId) {
        return reviewRepository.findByServiceId(serviceId);
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review review) {
        Review reviewToUpdate = getReviewById(review.getId());
        if(reviewToUpdate != null){
            reviewToUpdate.setTitle(review.getTitle());
            reviewToUpdate.setDescription(review.getDescription());
            reviewToUpdate.setServiceName(review.getServiceName());
            reviewToUpdate.setStars(review.getStars());
            reviewToUpdate.setAuthorId(review.getAuthorId());
            reviewToUpdate.setServiceId(review.getServiceId());
            reviewToUpdate.setInfluencerId(review.getId());

            return reviewRepository.save(reviewToUpdate);
        }
        else{
            return null;
        }
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public void validateReview(Review review) {
        if(review == null){
            throw new ValidationException("La reseña no puede ser nula");
        }
        if(review.getTitle() == null || review.getTitle().isEmpty()){
            throw new ValidationException("La reseña necesita un título");
        }
        if(review.getTitle().length()>150){
            throw new ValidationException("El título de la reseña es muy largo");
        }
        if(review.getDescription() == null || review.getDescription().isEmpty()){
            throw new ValidationException("La reseña necesita descripción");
        }
        if(review.getDescription().length()>350){
            throw new ValidationException("La descripción es demasiado extensa");
        }
        if(review.getServiceName() == null || review.getServiceName().isEmpty()){
            throw new ValidationException("La reseña necesita el nombre del servicio");
        }
        if(review.getServiceName().length() > 150){
            throw new ValidationException("El nombre del servicio de la reseña es extenso");
        }
        if(review.getStars() <= 0 || review.getStars() >5){
            throw new ValidationException("La reseña requiere calificación");
        }
        if(review.getServiceId()== null){
            throw new ValidationException("La reseña necesita especificar el servicio");
        }
        if(review.getAuthorId() == null){
            throw new ValidationException("La reseña necesita un autor");
        }
        if(review.getInfluencerId() == null){
            throw new ValidationException("La reseña necesita estar asignado a un influencer");
        }
    }
}
