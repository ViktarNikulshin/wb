package com.vnikulshin.wb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ParserWbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParserWbApplication.class, args);
	}

}
