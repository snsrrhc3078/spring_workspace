package com.edu.springboard.model;

import lombok.Data;

@Data
public class Photo {
	private int photo_idx;
	private String filename;
	private Gallery gallery;
}
