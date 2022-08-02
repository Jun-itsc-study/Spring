package com.koreait.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(Map<String, Object> map);

	int insertMember(MemberDTO dto);

	List<MemberDTO> selectAllMember();

	int updateMember(MemberDTO dto);

	int deleteMember(String id);

}
