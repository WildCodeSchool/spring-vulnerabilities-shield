package fr.wcs.vulns;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VulnTestController {


    @GetMapping("/passwords")
    public List<String> getPasswords() {
        return List.of("pass1", "12345", "oool");
    }
}
