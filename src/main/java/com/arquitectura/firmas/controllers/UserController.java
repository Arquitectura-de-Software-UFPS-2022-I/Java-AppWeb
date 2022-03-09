package com.arquitectura.firmas.controllers;

import com.arquitectura.firmas.repository.services.IUsuarioService;

import models.UserDto;
import services.impl.ApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUsuarioService userService;


    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception{
        return ResponseEntity.ok(new ApiService().getUserService().getUser());
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@RequestBody UserDto user) throws Exception{
        return ResponseEntity.ok(new ApiService().getUserService().loginUser(user.getUsername(), user.getPassword()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) throws Exception{
        return ResponseEntity.ok(new ApiService().getUserService().getUserId(id));
    }

    @PostMapping("/save")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> save(@RequestBody UserDto user) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiService().getUserService().saveUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserDto user) throws Exception{
        return ResponseEntity.ok(new ApiService().getUserService().updateUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id) throws Exception{
    	new ApiService().getUserService().deleteUser(id);
        return ResponseEntity.ok().build();
    }
}