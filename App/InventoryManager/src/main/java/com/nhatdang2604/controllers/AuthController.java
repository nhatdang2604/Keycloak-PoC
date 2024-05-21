package com.nhatdang2604.controllers;

import com.nhatdang2604.dtos.ProductDto;
import com.nhatdang2604.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @GetMapping("/access-denied")
    public ResponseEntity<?> denyAccess() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                    "{\n" +
                    "    \"status\": 401,\n" +
                    "    \"message\": \"You don't have permission for this end-point\"\n" +
                    "}"
                );
    }
}