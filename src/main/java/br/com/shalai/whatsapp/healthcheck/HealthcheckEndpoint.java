package br.com.shalai.whatsapp.healthcheck;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/healthcheck")
public class HealthcheckEndpoint {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/public")
    public ResponseEntity<String> publicHealthcheck() {
        return ResponseEntity.ok("200 [OK]");
    }

    @PreAuthorize("isAuthenticated() and hasAnyRole('COMMON', 'ADMIN')")
    @GetMapping("/protected")
    public ResponseEntity<String> protectedHealthcheck() {
        return ResponseEntity.ok("200 [OK]");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private")
    public ResponseEntity<String> privateHealthcheck() {
        return ResponseEntity.ok("200 [OK]");
    }
}
