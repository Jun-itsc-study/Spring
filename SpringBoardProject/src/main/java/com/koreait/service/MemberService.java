package com.koreait.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.koreait.dto.MemberDTO;
import com.koreait.mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper mapper;

	public MemberService(MemberMapper mapper) {
		this.mapper = mapper;
	}

	public MemberDTO login(String id, String passwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("passwd", passwd);
		return mapper.login(map);
	}

	public int insertMember(MemberDTO dto) {
		return mapper.insertMember(dto);		
	}

	public List<MemberDTO> selectAllMember() {
		return mapper.selectAllMember();
	}

	public int updateMember(MemberDTO dto) {
		return mapper.updateMember(dto);
	}

	public int deleteMember(String id) {
		return mapper.deleteMember(id);
	}
	
	
}
