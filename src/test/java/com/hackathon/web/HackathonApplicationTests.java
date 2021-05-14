package com.hackathon.web;

import com.hackathon.web.domain.Administrator;
import com.hackathon.web.repositories.AdministratorRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
class HackathonApplicationTests {

	@Autowired
	private AdministratorRepository repository;

	@Test
	void contextLoads() {

		Administrator administrator = repository.save(new Administrator());
		List<Administrator> administrators = repository.findAdministratorsByName("test");
		System.out.println(administrator);
		System.out.println(administrators.get(0).getAdminType());

		//repository.deleteById(2L);


	}

}
