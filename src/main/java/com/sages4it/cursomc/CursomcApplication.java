package com.sages4it.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sages4it.cursomc.services.S3Service;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
@Autowired
private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	public void run(String... args) throws Exception {
		s3Service.uploadFile("//Users//sergiocruz//Documents//ws//marcos.jpeg");
	}

}
