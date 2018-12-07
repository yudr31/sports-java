package com.spring.boot.sports;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.spring.boot.sports.mapper")
@MapperScan("com.spring.boot.sports.mapper")
public class SprotsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprotsApplication.class, args);
	}
}
