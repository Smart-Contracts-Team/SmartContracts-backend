package com.SmartContracts.upc.smartcontract.controller;

import com.SmartContracts.upc.smartcontract.model.Review;
import com.SmartContracts.upc.smartcontract.model.ServiceU;
import com.SmartContracts.upc.smartcontract.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/smartcontract/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    // URL: http://localhost:8080/api/smartcontract/v1/review
    // Method: GET

    @Transactional
    @GetMapping
    public ResponseEntity<List<Review>>getAllReviews(){
        if(reviewService.getAllReviews().isEmpty()){
            return new ResponseEntity<List<Review>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Review>>(reviewService.getAllReviews(),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/review/{reviewId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable(name="reviewId")Long reviewId){
        if(reviewService.getReviewById(reviewId) == null){
            return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Review>(reviewService.getReviewById(reviewId),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/review/service/{serviceId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<Review>>getReviewByServiceId(@PathVariable(name="serviceId")Long serviceId ){
        if(reviewService.getReviewsByServiceId(serviceId) == null){
            return new ResponseEntity<List<Review>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Review>>(reviewService.getReviewsByServiceId(serviceId),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/review/influencer/{influencerId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<Review>>getReviewsByInfluencerId(@PathVariable(name="influencerId")Long influencerId ){
        if(reviewService.getReviewsByInfluencerId(influencerId) == null){
            return new ResponseEntity<List<Review>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Review>>(reviewService.getReviewsByInfluencerId(influencerId),HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/review
    // Method: POST
    @Transactional
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        reviewService.validateReview(review);

        return new ResponseEntity<Review>(reviewService.createReview(review),HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/review/{reviewId}
    // Method: PUT
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable(name="serviceId")Long serviceId, @RequestBody Review review){
        if(reviewService.getReviewById(serviceId) == null){
            return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
        }

        reviewService.validateReview(review);
        return new ResponseEntity<Review>(reviewService.updateReview(review),HttpStatus.OK);
    }


}
