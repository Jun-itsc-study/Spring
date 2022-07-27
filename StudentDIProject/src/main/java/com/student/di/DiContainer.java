package com.student.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.student.config.DBManager;
import com.student.controller.HandlerMapping;
import com.student.dao.StudentDAO;
import com.student.service.StudentService;

@Configuration
public class DiContainer {
	@Bean
	public DBManager manager() {
		return new DBManager();
	}
	@Bean
	public StudentDAO dao() {
		return new StudentDAO();
	}
	@Bean
	public StudentService service() {
		return new StudentService();
	}
	@Bean
	public HandlerMapping handler() {
		return new HandlerMapping();
	}
}
