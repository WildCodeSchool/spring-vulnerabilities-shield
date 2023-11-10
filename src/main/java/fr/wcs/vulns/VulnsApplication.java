package fr.wcs.vulns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class VulnsApplication {

	@Autowired
	VulnsTestRepository repository;

	@Autowired
	VulnsUnsafeRepository unsafeRepository;

	@PostConstruct
	public void onStartup() {
		System.out.println("started");

		var v = new VulnsEntity();
		v.setName("csrf");
		repository.save(v);

		v = new VulnsEntity();
		v.setName("sqlinjection");
		repository.save(v);

		v = new VulnsEntity();
		v.setName("brokenaccesscontrol");
		repository.save(v);

		v = new VulnsEntity();
		v.setName("cryptographicfailure");
		repository.save(v);

		System.out.println(repository.count() + " vulns in db");

		System.out.println("lastVulns =" + repository.getRecentVulns(1L));
	}

	public static void main(String[] args) {
		SpringApplication.run(VulnsApplication.class, args);
	}

}
