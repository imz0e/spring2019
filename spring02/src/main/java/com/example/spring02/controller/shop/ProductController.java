package com.example.spring02.controller.shop;

import java.io.File;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.ProductDTO;
import com.example.spring02.service.shop.ProductService;

@Controller
@RequestMapping("/shop/product/*")	// 공통 url
public class ProductController {

	@Inject
	ProductService productService;
	
	/***************************************************************************
	 * @Method 설명 : 상품목록
	 * @작성일   : 2019. 6. 13.
	 * @작성자   : LJY
	 *************************************************************************/
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("/shop/product_list");
		mav.addObject("list", productService.listProduct());
		return mav;
	}
	
	/***************************************************************************
	 * @Method 설명 : 상품 상세정보
	 * @작성일   : 2019. 6. 13.
	 * @작성자   : LJY
	 *************************************************************************/
	@RequestMapping("detail/{product_id}")
	public ModelAndView detail(@PathVariable int product_id, ModelAndView mav) {
		mav.setViewName("/shop/product_detail");
		mav.addObject("dto", productService.detailProduct(product_id));
		return mav;
	}
	
	/***************************************************************************
	 * @Method 설명 : 상품등록
	 * @작성일   : 2019. 6. 17.
	 * @작성자   : LJY
	 *************************************************************************/
	@RequestMapping("write.do")
	public String write() {
		return "shop/product_write";
	}
	@RequestMapping("insert.do")
	public String insert(ProductDTO dto) {
		String filename = "-";
		if(!dto.getFile1().isEmpty()) {
			filename = dto.getFile1().getOriginalFilename();
			String path = "C:\\z0boot\\spring02\\src\\main\\webapp\\WEB-INF\\views\\images\\";
			try {
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path+filename));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		dto.setPicture_url(filename);
		productService.insertProduct(dto);
		return "redirect:/shop/product/list.do";
	}
	
	/***************************************************************************
	 * @Method 설명 : 상품수정
	 * @작성일   : 2019. 6. 17.
	 * @작성자   : LJY
	 *************************************************************************/
	@RequestMapping("edit/{product_id}")
	public ModelAndView edit(@PathVariable("product_id") int product_id, ModelAndView mav) {
		mav.setViewName("/shop/product_edit");
		mav.addObject("dto", productService.detailProduct(product_id));
		return mav;
	}
	@RequestMapping("update.do")
	public String update(ProductDTO dto) {
		String filename = "-";
		if(!dto.getFile1().isEmpty()) {
			filename = dto.getFile1().getOriginalFilename();
			String path = "C:\\z0boot\\spring02\\src\\main\\webapp\\WEB-INF\\views\\images\\";
			try {
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path+filename));
			} catch(Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);
		} else {
			ProductDTO dto2 = productService.detailProduct(dto.getProduct_id());
			dto.setPicture_url(dto2.getPicture_url());
		}
		productService.updateProduct(dto);
		return "redirect:/shop/product/list.do";
	}
	
	/***************************************************************************
	 * @Method 설명 : 상품삭제
	 * @작성일   : 2019. 6. 17.
	 * @작성자   : LJY
	 *************************************************************************/
	@RequestMapping("delete.do")
	public String delete(@RequestParam int product_id) {
		// 첨부파일의 이름
		String filename  = productService.fileInfo(product_id);
		if(filename != null && !filename.equals("-")) {
			String path = "C:\\z0boot\\spring02\\src\\main\\webapp\\WEB-INF\\views\\images\\";
			File f = new File(path+filename);
			if(f.exists()) {
				f.delete();
			}
		}
		productService.deleteProduct(product_id);
		return "redirect:/shop/product/list.do";
	}
	
}
