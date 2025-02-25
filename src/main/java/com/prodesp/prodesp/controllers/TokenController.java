/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.controllers;

import com.prodesp.prodesp.dtos.TokenDTO;
import com.prodesp.prodesp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private JwtUtil jwtUtil;
    
    @GetMapping
    public ResponseEntity<TokenDTO> gerarToken() {
        TokenDTO t = new TokenDTO();
        t.setSub(jwtUtil.generateToken("prodesp"));
        return ResponseEntity.ok(t);
    }
}
