package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.exception.ProductException;
import com.project.exception.UserException;
import com.project.model.Cart;
import com.project.model.User;
import com.project.request.AddItemRequest;
import com.project.response.ApiResponse;
import com.project.service.CartService;
import com.project.service.UserService;

@RestController
@RequestMapping("/api/cart")
//@Tag(name = "Cart Management" , description="find user cart,add item to cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
//	@Operation(description = "find cart by user id")
	public ResponseEntity<Cart>findUserCart(@RequestHeader("Authorization")String jwt) throws UserException{
		User user = userService.findUserProfileByJwt(jwt);
		Cart cart = cartService.findUserCart(user.getId());
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/add")
//	@Operation(description = "add item to cart")
	public ResponseEntity<ApiResponse>addItemToCart(@RequestBody AddItemRequest req,
			@RequestHeader("Authorization")String jwt) throws UserException,ProductException{
		User user = userService.findUserProfileByJwt(jwt);
		
	    cartService.addCartItem(user.getId(), req);
	    
	    ApiResponse res = new ApiResponse();
	    res.setMessage("item added to cart");
	    res.setStatus(true);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
