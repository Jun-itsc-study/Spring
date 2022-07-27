package com.example.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiContainer {
    @Bean
    public Greeting greeting() {
        Greeting g = new Greeting(1, "test");
        return g;
    }
    @Bean
    public MemberService service() {
    	MemberService member = new MemberService();
    	return member;
    }
}