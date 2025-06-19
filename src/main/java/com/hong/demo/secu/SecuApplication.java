package com.hong.demo.secu; 

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.hong.demo.secu.domain.*; 

@SpringBootApplication
public class SecuApplication implements CommandLineRunner {

	private final CarRepository repository;
	private final OwnerRepository orepository;
	private final AppUserRepository urepository;
	private final PasswordEncoder passwordEncoder;

	public SecuApplication (
		CarRepository repository, 
		OwnerRepository orepository,
		AppUserRepository urepository,
		PasswordEncoder passwordEncoder
	) {
		this.repository = repository;
		this.orepository = orepository;
		this.urepository = urepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(SecuApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Add owner objects and save these to db
		Owner owner1 = new Owner("Tommy", "Le");
		Owner owner2 = new Owner("Hong", "Le");
		orepository.saveAll(Arrays.asList(owner1, owner2));

		// Add cars
		repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2023, 59000, owner1));
		repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2025, 29000, owner2));
		repository.save(new Car("VinFast", "Everest", "Silver", "VF-8E", 2024, 39000, owner2));

		// Username: user, password: user
		urepository.save(new AppUser("user", passwordEncoder.encode("user"), "USER"));
		// Username: admin, password: admin
		urepository.save(new AppUser("admin", passwordEncoder.encode("admin"), "ADMIN"));
	}

}
