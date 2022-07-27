package com.student.controller;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.student.di.DiContainer;
import com.student.exception.StudentException;
import com.student.service.StudentService;
import com.student.vo.StudentVO;

public class SelectTopStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DiContainer.class);
		try {
			ArrayList<StudentVO> list = ctx.getBean("service",StudentService.class).selectRankOne();
			
			for(int i=0;i<list.size();i++)
				System.out.println(list.get(i));
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}
	}

}
