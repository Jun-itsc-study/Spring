package com.koreait.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.koreait.dto.BoardCommentDTO;
import com.koreait.dto.BoardDTO;
import com.koreait.dto.FileDTO;
import com.koreait.dto.MemberDTO;
import com.koreait.mapper.BoardMapper;

@Service
public class BoardService {
	private BoardMapper mapper;

	public BoardService(BoardMapper mapper) {
		this.mapper = mapper;
	}

	public List<BoardDTO> selectBoardList(int pageNo) {
		return mapper.selectBoardList(pageNo);
	}

	public int selectBoardCount() {
		return mapper.selectBoardCount();
	}

	public BoardDTO selectBoardDTO(int bno) {
		return mapper.selectBoardDTO(bno);
	}

	public List<FileDTO> selectFileList(int bno) {
		return mapper.selectFileList(bno);
	}

	public List<BoardCommentDTO> selectCommentDTO(int bno) {
		return mapper.selectCommentDTO(bno);
	}

	public int addBoardCount(int bno) {
		return mapper.addBoardCount(bno);
	}
	
	public int deleteBoard(int bno) {
		return mapper.deleteBoard(bno);
	}

	public int insertComment(BoardCommentDTO dto) {
		return mapper.insertComment(dto);
	}

	public int plusLike(Map<String, Object> map) {
		return mapper.plusLike(map);
	}
	
	public int plusDislike(Map<String, Object> map) {
		return mapper.plusDislike(map);
	}

	public int minusLike(Map<String, Object> map) {
		return mapper.minusLike(map);
	}

	public int minusDislike(Map<String, Object> map) {
		return mapper.minusDislike(map);
	}

	public int boardWrite(BoardDTO dto) {
		int bno = mapper.selectBoardNo();
		dto.setBno(bno);
		mapper.insertBoard(dto);
		return bno;
	}

	public void fileUpload(FileDTO file) {
		mapper.fileUpload(file);
	}

	public String fileDown(FileDTO dto) {
		return mapper.fileDown(dto);
	}
}




