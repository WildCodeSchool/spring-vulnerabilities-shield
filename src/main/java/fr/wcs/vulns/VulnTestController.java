package fr.wcs.vulns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VulnTestController {


	@Autowired
	TestRepository repository;

	@Autowired
	UnsafeTestRepository unsafeRepository;

    @GetMapping("/passwords")
    public List<String> getPasswords() {
        return List.of("pass1", "12345", "oool");
    }

    @GetMapping("/getTestEntitiesByName")
    public List<String> getTestEntitiesByName(@RequestParam String name) {
        TestEntity testEntity = repository.findByName(name);
        return testEntity == null ? new ArrayList<>() : List.of(testEntity.getName());
    }

    /**
     * Call me with 
     * http://localhost:8080/getEntitiesByName_unsafe?name=%27OR%271%27=%271
     * to show SQL injection
     */
    @GetMapping("/getTestEntitiesByName_unsafe")
    public List<String> getTestEntitiesByNameUnsafe(@RequestParam String name) {
        List<TestEntity> testEntities = unsafeRepository.getFromNameUnsafe(name);
        return testEntities.stream().map(e -> e.getName()).toList();
    }
}
