package com.edu.springboard.model.gallery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.edu.springboard.domain.Gallery;
import com.edu.springboard.domain.Photo;
import com.edu.springboard.exception.PhotoException;

@Repository
public class JdbcPhotoDAO implements PhotoDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List selectAll() {
		String sql = "select * from photo order by photo_idx desc";
		List list = jdbcTemplate.query(sql, new RowMapper<Photo>() {
			@Override
			public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Photo photo = new Photo();
				Gallery gallery = new Gallery();
				photo.setPhoto_idx(rs.getInt("photo_idx"));
				photo.setFilename(rs.getString("filename"));
				
				gallery.setGallery_idx(rs.getInt("gallery_idx"));
				photo.setGallery(gallery);
				return photo;
			}
		});
		return list;
	}

	public Photo select(int photo_idx) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from photo");
		sb.append(" where gallery_idx=?");
		Photo photo = jdbcTemplate.queryForObject(sb.toString(), new Object[]{photo_idx}, new RowMapper<Photo>() {
			@Override
			public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Photo photo = new Photo();
				Gallery gallery = new Gallery();
				photo.setPhoto_idx(rs.getInt("photo_idx"));
				photo.setFilename(rs.getString("filename"));
				
				gallery.setGallery_idx(rs.getInt("gallery_idx"));
				photo.setGallery(gallery);
				return photo;
			}
		});
		
		return photo;
	}

	public void insert(Photo photo) throws PhotoException{
		StringBuilder sb = new StringBuilder();
		sb.append("insert into photo(photo_idx, filename, gallery_idx)");
		sb.append("values (seq_photo.nextval, ?, ?)");
		int result = jdbcTemplate.update(sb.toString(), new Object[] {photo.getFilename(), photo.getGallery().getGallery_idx()});
		if(result<1) {
			throw new PhotoException("사진등록 실패");
		}
	}

	public void delete(int photo_idx) throws PhotoException{
		String sql = "delete from photo where photo_idx=?";
		int result = jdbcTemplate.update(sql, new Object[] {photo_idx});
		if(result<1) {
			throw new PhotoException("사진삭제 실패");
		}
	}
}
