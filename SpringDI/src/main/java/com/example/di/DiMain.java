package com.example.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DiMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DiContainer.class);
		Greeting g1 = ctx.getBean("greeting", Greeting.class);
		System.out.println(g1);
		Greeting g2 = ctx.getBean("greeting", Greeting.class);
		System.out.println(g2);
		System.out.println(g1 == g2);
		
		MemberService m1 = ctx.getBean("service",MemberService.class);
		m1.selectMember();
		
	}
}