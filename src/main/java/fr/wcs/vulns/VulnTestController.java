package fr.wcs.vulns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VulnTestController {


	@Autowired
	VulnsTestRepository repository;

	@Autowired
	VulnsUnsafeRepository unsafeRepository;

    @GetMapping("/passwords")
    public List<String> getPasswords() {
        return List.of("pass1", "12345", "oool");
    }

    @GetMapping("/getVulnsByName")
    public List<String> getVulnsByName(@RequestParam String name) {
        VulnsEntity vuln = repository.findByName(name);
        return vuln == null ? new ArrayList<>() : List.of(vuln.getName());
    }

    /**
     * Call me with 
     * http://localhost:8080/getVulnsByName_unsafe?name=%27OR%271%27=%271
     * to show SQL injection
     */
    @GetMapping("/getVulnsByName_unsafe")
    public List<String> getVulnsByNameUnsafe(@RequestParam String name) {
        List<VulnsEntity> vulns = unsafeRepository.getFromNameUnsafe(name);
        return vulns.stream().map(e -> e.getName()).toList();
    }
}
