package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dto.StudentDTO;
import com.example.mapper.StudentMapper;

@Service
public class StudentService {
	private StudentMapper mapper;
	
	public StudentService(StudentMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<StudentDTO> selectAll() {
		return mapper.selectAll();
	}

	public List<StudentDTO> searchStudent(String kind, String search) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kind", kind);
		if(kind.equals("score")) map.put("search", Double.parseDouble(search));
		else map.put("search", search);
		return mapper.searchStudent(map);
	}

}
