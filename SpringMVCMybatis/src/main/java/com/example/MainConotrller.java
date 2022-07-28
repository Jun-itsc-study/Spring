package com.example;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.MemberDTO;
import com.example.service.MemberService;

@Controller
public class MainConotrller {
	private MemberService service;

	public MainConotrller(MemberService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/")
	public String main(Model model) {
		//전체 회원정보 읽어와서 출력
		List<MemberDTO> list = service.selectAllMember();
		model.addAttribute("list", list);
		return "main";
	}
	@RequestMapping("/register.do")
	public String register(MemberDTO dto) {
		service.insertMember(dto);
		return "redirect:/";
	}
	
	@RequestMapping("/delete.do")
	public String delete(String id) {
		service.deleteMember(id);
		return "redirect:/";
	}
	
	@RequestMapping("/updateView.do")
	public String updateView(String id, Model model) {
		model.addAttribute("dto",service.selectMember(id));
		return "updateView";
	}
	
	@RequestMapping("/update.do")
	public String update(MemberDTO dto) {
		service.updateMember(dto);
		return "redirect:/";
	}
}