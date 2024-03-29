package com.edu.mvc3.model;

import java.util.List;

import com.edu.mvc3.domain.Gallery;

public interface GalleryService {
	public List SelectAll();
	public Gallery select(int gallery_idx);
	public void regist(Gallery gallery);
	public void update(Gallery gallery);
	public void delete(int gallery_idx);
}
