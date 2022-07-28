package com.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	private MainService service;
	private PersonDTO dto;
	public MainController(MainService service, PersonDTO dto) {
		this.service = service;
		this.dto=dto;
	}
	
	@RequestMapping("/")
	public String test() {
		System.out.println("test()");
		service.testService();
		System.out.println(dto.toString());
		return "test";
	}
	@RequestMapping("/form.do")
	public String form(HttpServletRequest req) {
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		System.out.println("name:"+name+", age:"+age);
		req.setAttribute("test", "세팅한 데이터");
		return "result";
	}
	@RequestMapping("/registerView.do")
	public String registerView() {
		return "register";
	}
	@RequestMapping("/register.do")
	public String register(RegisterDTO dto, Model model) {
		//request 를 안써도 넘어오는 데이터와 이름이 같으면 자동으로 매칭
		//String id=req.getParameter("id");
		//String pass=req.getParameter("pass");
		//String name=req.getParameter("name");
		//int age=Integer.parseInt(req.getParameter("age"));
		
		//public String register(String id, String pass, String name, int age) {
		//RegisterDTO dto = new RegisterDTO(id, pass, name, age);
		
		//DTO 도 타입맞으면 DTO로 지정해도 받아짐
		System.out.println(dto);
//		req.setAttribute("dto", dto);
		model.addAttribute("dto",dto);
		return "register_result";
	}
	@RequestMapping("/loginView.do")
	public String loginView() {
		return "login";
	}
	@RequestMapping("/login.do")
	public String login(String id, String pass, HttpSession session) {
		session.setAttribute("id", id);
		session.setAttribute("pass", pass);
		return "login_result";
	}
}