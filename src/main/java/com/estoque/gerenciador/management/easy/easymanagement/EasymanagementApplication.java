package com.estoque.gerenciador.management.easy.easymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Habilitar auditoria
public class EasymanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasymanagementApplication.class, args);
	}

}
