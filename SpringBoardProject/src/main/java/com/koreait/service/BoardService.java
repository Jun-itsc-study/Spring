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

	public int commentPlusLike(BoardCommentDTO dto) {
		return mapper.commentPlusLike(dto);
	}

	public int commentPlusDislike(BoardCommentDTO dto) {
		return mapper.commentPlusDislike(dto);
	}

	public int commentMinusLike(BoardCommentDTO dto) {
		return mapper.commentMinusLike(dto);
	}

	public int commentMinusDislike(BoardCommentDTO dto) {
		return mapper.commentMinusDislike(dto);
	}

	public int commentDelete(BoardCommentDTO dto) {
		return mapper.commentDelete(dto);
	}

	public int insertImage(String path) {
		int fno = mapper.selectBoardImageNo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fno", fno);
		map.put("path", path);
		mapper.insertBoardImage(map);
		return fno;
	}

	public String selectImageFile(int fno) {
		return mapper.selectImageFile(fno);
	}

	public List<BoardQnaDTO> selectQnaList(String id) {
		return mapper.selectQnaList(id);
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

//	public int selectMaxRN() {
//		return mapper.selectMaxRN();
//	}
//
//	public int selectMinRN() {
//		return mapper.selectMinRN();
//	}
//
//	public int selectRownum(int bno) {
//		return mapper.selectRownum(bno);
//	}
//
//	public int selectPNBoard(int rn) {
//		return mapper.selectPNBoard(rn);
//	}

	public int nextBoard(int bno) {
		return mapper.nextBoard(bno);
	}
	public int prevBoard(int bno) {
		return mapper.prevBoard(bno);
	}
}




