package com.koreait.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(Map<String, Object> map);

}
