package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	List<MemberDTO> selectAllMember();

	int insertMember(MemberDTO dto);

	int deleteMember(String id);
	
	MemberDTO selectMember(String id);

	int updateMember(MemberDTO dto);
}