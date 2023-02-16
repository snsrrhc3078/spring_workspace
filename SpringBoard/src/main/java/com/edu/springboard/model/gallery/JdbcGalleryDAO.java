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
import com.edu.springboard.exception.GalleryException;

@Repository
public class JdbcGalleryDAO implements GalleryDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List selectAll() {
		//내부적으로 jdbc를 사용하지만, 다 숨겨놓았다, 즉 개발자로 하여금 기존의
		//jdbc의 상투적 코드를 작성할 필요없게 해놓은 것 뿐이지, 기즌의 jdbc가 맞다
		String sql = "select * from gallery order by gallery_idx desc";
		return jdbcTemplate.query(sql, new RowMapper<Gallery>() {

			@Override
			public Gallery mapRow(ResultSet rs, int rowNum) throws SQLException {
				Gallery gallery = new Gallery();
				gallery.setGallery_idx(rs.getInt("gallery_idx"));
				gallery.setTitle(rs.getString("title"));
				gallery.setWriter(rs.getString("writer"));
				gallery.setContent(rs.getString("content"));
				gallery.setRegdate(rs.getString("regdate"));
				gallery.setHit(rs.getInt("hit"));
				
				//GalleryDTO 가 보유한 photoList를 채우기
				String sql = "select * from photo where gallery_idx=?";
				List<Photo> photoList = jdbcTemplate.query(sql, new Object[] {gallery.getGallery_idx()}, new RowMapper<Photo>() {
					@Override
					public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
						Photo photo = new Photo();
						photo.setPhoto_idx(rs.getInt("photo_idx"));
						photo.setFilename(rs.getString("filename"));
						return photo;
					}
				});
				
				gallery.setPhotoList(photoList);
				return gallery;
			}
		});
	}

	public Gallery select(int gallery_idx) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from gallery");
		sb.append(" where gallery_idx=?");
		
		Gallery gallery = jdbcTemplate.queryForObject(sb.toString(), new Object[] {gallery_idx}, new RowMapper<Gallery>() {
			@Override
			public Gallery mapRow(ResultSet rs, int rowNum) throws SQLException {
				Gallery gallery = new Gallery();
				gallery.setGallery_idx(rs.getInt("gallery_idx"));
				gallery.setTitle(rs.getString("title"));
				gallery.setWriter(rs.getString("writer"));
				gallery.setContent(rs.getString("content"));
				gallery.setRegdate(rs.getString("regdate"));
				gallery.setHit(rs.getInt("hit"));
				
				String sql = "select * from photo where gallery_idx=?";
				List<Photo> photoList = jdbcTemplate.query(sql, new Object[] {gallery.getGallery_idx()}, new RowMapper<Photo>() {
					@Override
					public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
						Photo photo = new Photo();
						photo.setPhoto_idx(rs.getInt("photo_idx"));
						photo.setFilename(rs.getString("filename"));
						Gallery gallery = new Gallery();
						gallery.setGallery_idx(rs.getInt("gallery_idx"));
						photo.setGallery(gallery);
						return photo;
					}
				});
				gallery.setPhotoList(photoList);
				return gallery;
			}
		});
		return gallery;
	}

	//CRUD는 모두 update() 사용하면 된다
	public void insert(Gallery gallery) throws GalleryException{
		StringBuilder sb = new StringBuilder();
		sb.append("insert into gallery(gallery_idx, title, writer, content)");
		sb.append("values (seq_gallery.nextval, ?, ?, ?)");
		int result = jdbcTemplate.update(sb.toString(), new Object[] {gallery.getTitle(), gallery.getWriter(), gallery.getContent()});
		
		//mybatis 처럼 insert 와 동시에 pk 얻기
		int gallery_idx = jdbcTemplate.queryForObject("select seq_gallery.currval as gallery_idx from dual", new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("gallery_idx");
			}
		});
		gallery.setGallery_idx(gallery_idx);
		
		if(result<1) {
			throw new GalleryException("등록 실패");
		}
	}

	public void update(Gallery gallery) throws GalleryException{
		StringBuilder sb = new StringBuilder();
		sb.append("update gallery set title=?, writer=?, content=?");
		sb.append(" where gallery_idx=?");
		int result = jdbcTemplate.update(sb.toString(), new Object[] {gallery.getTitle(), gallery.getWriter(), gallery.getContent(),gallery.getGallery_idx()});
		if(result<1) {
			throw new GalleryException("수정 실패");
		}
	}

	public void delete(int gallery_idx) throws GalleryException{
		StringBuilder sb = new StringBuilder();
		sb.append("delete from gallery");
		sb.append(" where gallery_idx=?");
		int result = jdbcTemplate.update(sb.toString(), new Object[] {gallery_idx});
		if(result<1) {
			throw new GalleryException("삭제 실패");
		}
	}
}
