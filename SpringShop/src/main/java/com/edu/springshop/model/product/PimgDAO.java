package com.edu.springshop.model.product;

import java.util.List;

import com.edu.springshop.domain.Pimg;

public interface PimgDAO {
	public List<Pimg> selectAll();
	public Pimg select(int pimg_idx);
	public List<Pimg> selectAllByFK(int product_idx);
	public void insert(Pimg pimg);
	public void delete(int pimg_idx);
}
