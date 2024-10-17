package com.rule.admin.Controller;

import com.rule.admin.Utils.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthCheckController {

    @GetMapping(Route.HEALTH)
    public ResponseEntity<HashMap<String, Object>> getHealth() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
