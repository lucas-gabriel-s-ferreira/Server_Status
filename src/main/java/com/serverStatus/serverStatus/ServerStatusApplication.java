package com.serverStatus.serverStatus;

import com.serverStatus.serverStatus.model.Server;
import com.serverStatus.serverStatus.model.enumeration.Status;
import com.serverStatus.serverStatus.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ServerStatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerStatusApplication.class, args);
		System.out.println("Server Status Application está rodando corretamente");
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository) {
		return args -> {
			Server s1 = new Server(null, "Server 1", "play.earthmc.net", "52", "Minecraft", Status.SERVER_UP);
			Server s2 = new Server(null, "Server 2", "jogar.rederevo.com", "52", "Minecraft", Status.SERVER_DOWN);
			Server s3 = new Server(null, "Server 3", "purplepriso", "52", "Minecraft", Status.SERVER_DOWN);
			Server s4 = new Server(null, "Server 4", "170.233.32.32", "52", "My pc", Status.SERVER_DOWN);

			serverRepository.saveAll(Arrays.asList(s1, s2, s3, s4));
		};
	}

}