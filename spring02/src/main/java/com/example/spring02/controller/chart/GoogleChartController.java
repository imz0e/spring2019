package com.example.spring02.controller.chart;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.service.chart.GoogleChartService;

@Controller	// json을 리턴하는 method가 있는 경우 사용
@RequestMapping("/chart/*")
public class GoogleChartController {

	@Inject
	GoogleChartService googleChartService;
	
	@RequestMapping("chart1.do")
	public ModelAndView chart1() {
		return new ModelAndView("chart/chart01");
	}
	
	@RequestMapping("chart2.do")
	public ModelAndView chart2() {
		return new ModelAndView("chart/chart02");
	}

	@ResponseBody
	@RequestMapping("cart_money_list.do")
	public JSONObject cart_money_list() {
		return googleChartService.getChartData();
	}
	
	
}
