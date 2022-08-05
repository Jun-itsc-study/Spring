package com.koreait.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.koreait.dto.BoardCommentDTO;
import com.koreait.dto.BoardDTO;
import com.koreait.dto.BoardQnaDTO;
import com.koreait.dto.FileDTO;
import com.koreait.dto.MemberDTO;
import com.koreait.mapper.BoardMapper;
import com.koreait.mapper.QnaMapper;

@Service
public class QnaService {
	private QnaMapper mapper;

	public QnaService(QnaMapper mapper) {
		this.mapper = mapper;
	}

	public List<BoardQnaDTO> selectQnaList(String writer, int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("writer", writer);
		map.put("page", page);
		return mapper.selectQnaList(map);
	}

	public List<BoardQnaDTO> selectQnaListAll() {
		return mapper.selectQnaListAll();
	}

	public int selectQnaCount(String id) {
		return mapper.selectQnaCount(id);
	}

	public int selectQnaCountAll() {
		return mapper.selectQnaCountAll();
	}

	public int insertQna(BoardQnaDTO dto) {
		return mapper.insertQna(dto);
	}

	public BoardQnaDTO selectQna(int qno) {
		return mapper.selectQna(qno);
	}

	public int changeStatus(int qno, int status) {
		Map<String, Integer> map = new HashMap<String, Integer>(); 
		map.put("qno", qno);
		map.put("status", status);
		return mapper.changeStatus(map);
	}

	public int insertResponse(BoardQnaDTO dto) {
		return mapper.insertResponse(dto);
	}
}




