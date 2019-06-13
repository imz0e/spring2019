package com.example.spring02.model.shop.dao;

import java.util.List;

import com.example.spring02.model.shop.dto.ProductDTO;

public interface ProductDAO {

	List<ProductDTO> listProduct();
	ProductDTO detailProduct(int productId);
	void updateProduct(ProductDTO dto);
	void deleteProduct(int productId);
	void insertProduct(ProductDTO dto);
	String fileInfo(int productId);
}
