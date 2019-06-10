package com.example.spring01.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring01.model.dto.MemberDTO;
import com.example.spring01.service.MemberService;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService memberService;
	
	/***************************************************************************
	 * @Method 설명 : 회원관리목록
	 * @작성일   : 2019. 6. 7.
	 * @작성자   : LJY
	 *************************************************************************/
	@RequestMapping("member/list.do")
	public String memberList(Model model) {
		List<MemberDTO> list = memberService.memberList();
		logger.info("회원목록:"+list);
		model.addAttribute("list", list);
		return"member/member_list";
	}
	
	/***************************************************************************
	 * @Method 설명 : 회원등록폼 이동
	 * @작성일   : 2019. 6. 7.
	 * @작성자   : LJY
	 *************************************************************************/
	@RequestMapping("member/write.do")
	public String write() {
		return "member/write";
	}
	
	/***************************************************************************
	 * @Method 설명 : 회원가입
	 * @작성일   : 2019. 6. 7.
	 * @작성자   : LJY
	 *************************************************************************/
	@RequestMapping("member/insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		memberService.insertMember(dto);
		return "redirect:/member/list.do";	// 목록갱신
	}
	
	/***************************************************************************
	 * @Method 설명 : 회원상세정보
	 * @작성일   : 2019. 6. 7.
	 * @작성자   : LJY
	 *************************************************************************/
	@RequestMapping("member/view.do")
	public String view(@RequestParam String userid, Model model) {
		model.addAttribute("dto", memberService.viewMember(userid));
		return "member/view";	// view.jsp로 포워딩
	}
	
	/***************************************************************************
	 * @Method 설명 : 회원정보수정
	 * @작성일   : 2019. 6. 7.
	 * @작성자   : LJY
	 *************************************************************************/
	@RequestMapping("member/update.do")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		// 비밀번호 확인
		boolean result = memberService.checkPw(dto.getUserid(), dto.getPasswd());
		logger.info("비밀번호 확인:" + result ); 
		if(result) {	// 비밀번호가 맞으면
			memberService.updateMember(dto);	// 레코드 수정
			return "redirect:/member/list.do";	// 목록으로 이동
		} else {	// 비밀번호가 틀리면
			MemberDTO dto2 = memberService.viewMember(dto.getUserid());
			dto.setJoin_date(dto2.getJoin_date());	// 날짜가 지워지지 않도록
			model.addAttribute("dto", dto);
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "member/view";
		}
	}
	
	/***************************************************************************
	 * @Method 설명 : 회원정보 삭제
	 * @작성일   : 2019. 6. 10.
	 * @작성자   : LJY
	 *************************************************************************/
	@RequestMapping("member/delete.do")
	public String delete(@RequestParam String userid
						, @RequestParam String passwd, Model model) {
		// 비밀번호 확인
		boolean result = memberService.checkPw(userid, passwd);
		logger.info("비밀번호 확인 : " + result);
		if(result) {
			memberService.deleteMember(userid);
			return "redirect:/member/list.do";
		} else {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("dto", memberService.viewMember(userid));
			return "member/view";
		}
	}
}
