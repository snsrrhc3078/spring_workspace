package com.edu.SpringMVC1.model.movie;

public class MovieReview{
	public String review(String movie_id) {
		String msg = null;
		if (movie_id.equals("m1")) {
			msg = "비주얼을 좋은데 스토리가 안좋음";
		}else if(movie_id.equals("m2")) {
			msg = "액션 맛집";
		}else if(movie_id.equals("m3")) {
			msg = "sf 걸작";
		}else if(movie_id.equals("m4")) {
			msg = "명작";
		}
		
		return msg;
	}
}
