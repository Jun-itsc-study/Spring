package com.example;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.MemberDTO;
import com.example.service.MemberService;

@RestController
@RequestMapping("/user")
public class UserController {
	private MemberService service;
	
	public UserController(MemberService service) {
		this.service = service;
	}

	@GetMapping(value = "/main.do")
	public ResponseEntity<List<MemberDTO>> main(){
		List<MemberDTO> list = service.selectAllMember();
		return ResponseEntity.ok(list);
	}
	@GetMapping(value = "/search.do")
	public ResponseEntity<List<MemberDTO>> search(String kind, String search){
		//검색 종류, 검색어 받아서 결과를 json으로 리턴
		List<MemberDTO> list = service.searchMember(kind, search);
		return ResponseEntity.ok(list);
		
	}
	
	
}