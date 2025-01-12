package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.exception.ProductException;
import com.project.model.Rating;
import com.project.model.User;
import com.project.request.RatingRequest;

public interface RatingService {

	public Rating createRating(RatingRequest req , User user) throws ProductException;
	public List<Rating> getProductsRating(Long productId);
	
}
