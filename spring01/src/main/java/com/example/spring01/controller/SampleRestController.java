package com.example.spring01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring01.model.dto.ProductDTO;

@RestController	// 스프링 4.0부터 사용 가능
public class SampleRestController {

	@ResponseBody	// json 형식으로 리턴(@RestController 사용 시에는 없어도 됨, @Controller 사용시에는 필요)
	@RequestMapping("test/doF")
	public ProductDTO doF() {
		// 호출한 곳으로 데이터가 넘어감(json 형식)
		return new ProductDTO("냉장고", 5000000);
	}
}
