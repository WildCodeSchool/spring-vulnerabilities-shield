package fr.wcs.vulns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class VulnsApplication {

	@Autowired
	TestRepository repository;

	@Autowired
	UnsafeTestRepository unsafeRepository;

	@PostConstruct
	public void onStartup() {
		System.out.println("started");

		var v = new TestEntity();
		v.setName("entity A");
		repository.save(v);

		v = new TestEntity();
		v.setName("entity B");
		repository.save(v);

		v = new TestEntity();
		v.setName("entity C");
		repository.save(v);

		v = new TestEntity();
		v.setName("entity D");
		repository.save(v);

		System.out.println(repository.count() + " testentity in db");

		System.out.println("lastTestEntities =" + repository.getRecent(1L));
	}

	public static void main(String[] args) {
		SpringApplication.run(VulnsApplication.class, args);
	}

}
