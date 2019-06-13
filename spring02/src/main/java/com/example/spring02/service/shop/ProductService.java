package com.example.spring02.service.shop;

import java.util.List;

import com.example.spring02.model.shop.dto.ProductDTO;

public interface ProductService {

	List<ProductDTO> listProduct();
	ProductDTO detailProduct(int productId);
	String fileInfo(int productId);
	void updateProduct(ProductDTO dto);
	void deleteProduct(int productId);
	void insertProduct(ProductDTO dto);
}
