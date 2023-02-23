package com.edu.springshop.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.edu.springshop.domain.Pimg;
import com.edu.springshop.domain.Product;
import com.edu.springshop.util.FileManager;

@Service
public class ProductServiceImpl implements ProductService{

	//DAO
	@Autowired
	@Qualifier("mybatisProductDAO")
	private ProductDAO productDAO;
	
	@Autowired
	@Qualifier("mybatisPimgDAO")
	private PimgDAO pimgDAO; 
	
	//filemanager
	@Autowired
	private FileManager fileManager;
	
	public List<Product> selectAll() {
		return productDAO.selectAll();
	}

	public Product select(int product_idx) {
		return productDAO.select(product_idx);
	}

	public void regist(Product product, String dir){
		
		//파일을 서버에 저장
		MultipartFile[] files = product.getFiles();
		List<String> filenameList = fileManager.saveFiles(files, dir);
		
		//상품저장 (부모 Product)
		productDAO.insert(product);
		
		//이미지 저장(Pimg)
		for(String filename : filenameList) {
			Pimg pimg = new Pimg();
			pimg.setProduct(product);
			pimg.setFilename(filename);
			pimgDAO.insert(pimg);
		}
	}

	public void update(Product product) {
		
	}

	public void delete(int product_idx) {
		
	}

}
