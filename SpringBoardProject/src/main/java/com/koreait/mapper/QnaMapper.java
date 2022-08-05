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
public interface QnaMapper {

	List<BoardQnaDTO> selectQnaListAll();
	int selectQnaCount(String id);
	int selectQnaCountAll();
	int insertQna(BoardQnaDTO dto);
	BoardQnaDTO selectQna(int qno);
	int changeStatus(Map<String, Integer> map);
	int insertResponse(BoardQnaDTO dto);
	List<BoardQnaDTO> selectQnaList(Map<String, Object> map);
}
