package com.edu.springshop.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.springshop.domain.Cart;

@Service
public class CartServiceimpl implements CartService{

	@Autowired
	private CartDAO cartDAO;
	
	public List selectAll(Cart cart) {
		return cartDAO.selectAll(cart);
	}

	public void regist(Cart cart){
		
	}

	public int selectCount(Cart cart) {
		return cartDAO.selectCount(cart);
	}

	public void updateEa(Cart cart) {
		
	}
		
}
