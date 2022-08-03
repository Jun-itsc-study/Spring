package com.example;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.StudentDTO;
import com.example.service.StudentService;

@Controller
public class MainController {
	private StudentService service;
	
	public MainController(StudentService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/")
	public String main() {return "student_search";}
	
	@RequestMapping("all.do")
	public void selectAll(HttpServletResponse res) throws IOException {
		List<StudentDTO> list = service.selectAll();
		JSONObject obj = new JSONObject();
		if(list.size()==0) {
			obj.put("code", 500);
			obj.put("message", "조회된 데이터가 없습니다.");
			obj.put("result", "NONE");
		} else {
			obj.put("code", 200);
			obj.put("message", "정상적으로 조회되었습니다.");
			obj.put("result", new JSONArray(list));
		}
		res.setContentType("text/html;charset=utf-8");
		res.getWriter().write(obj.toString());
	}
	
	@RequestMapping("search.do")
	public void search(String kind, String search, HttpServletResponse res) throws IOException {
		List<StudentDTO> list = service.searchStudent(kind, search);
		JSONObject obj = new JSONObject();
		if(list.size()==0) {
			obj.put("code", 500);
			obj.put("message", "조회된 데이터가 없습니다.");
			obj.put("result", "NONE");
		} else {
			obj.put("code", 200);
			obj.put("message", "정상적으로 조회되었습니다.");
			obj.put("result", new JSONArray(list));
		}
		res.setContentType("text/html;charset=utf-8");
		res.getWriter().write(obj.toString());
		
	}
}
