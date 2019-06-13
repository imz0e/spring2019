package com.example.spring02.model.shop.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.shop.dto.ProductDTO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<ProductDTO> listProduct() {
		return sqlSession.selectList("product.list_product");
	}

	@Override
	public ProductDTO detailProduct(int productId) {
		return sqlSession.selectOne("product.detail_product", productId);
	}

	@Override
	public void updateProduct(ProductDTO dto) {
		sqlSession.update("product.update_product", dto);
	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertProduct(ProductDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public String fileInfo(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
