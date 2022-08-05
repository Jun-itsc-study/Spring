package com.koreait.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.dto.BoardCommentDTO;
import com.koreait.dto.BoardDTO;
import com.koreait.dto.BoardQnaDTO;
import com.koreait.dto.FileDTO;
import com.koreait.dto.MemberDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> selectBoardList(int pageNo);
	int selectBoardCount();
	BoardDTO selectBoardDTO(int bno);
	List<FileDTO> selectFileList(int bno);
	List<BoardCommentDTO> selectCommentDTO(int bno);
	int addBoardCount(int bno);
	int deleteBoard(int bno);
	int insertComment(BoardCommentDTO dto);
	int plusLike(Map<String, Object> map);
	int plusDislike(Map<String, Object> map);
	int minusLike(Map<String, Object> map);
	int minusDislike(Map<String, Object> map);
	int insertBoard(BoardDTO dto);
	int selectBoardNo();
	void fileUpload(FileDTO file);
	String fileDown(FileDTO dto);
	int commentPlusLike(BoardCommentDTO dto);
	int commentPlusDislike(BoardCommentDTO dto);
	int commentMinusLike(BoardCommentDTO dto);
	int commentMinusDislike(BoardCommentDTO dto);
	int commentDelete(BoardCommentDTO dto);
	int selectBoardImageNo();
	void insertBoardImage(Map<String, Object> map);
	String selectImageFile(int fno);
	List<BoardQnaDTO> selectQnaList(String id);
	List<BoardQnaDTO> selectQnaListAll();
	int selectQnaCount(String id);
	int selectQnaCountAll();
	int insertQna(BoardQnaDTO dto);
	BoardQnaDTO selectQna(int qno);
	int changeStatus(Map<String, Integer> map);
	int insertResponse(BoardQnaDTO dto);
}
