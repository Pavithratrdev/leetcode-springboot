package com.leet.code.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.leet.code.dao.UserDAO;
import com.leet.code.model.Response;
import com.leet.code.model.UserModel;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired 
	UserDAO dao;

    @PostMapping("/create")
    public ResponseEntity<Response> createUser(@RequestBody UserModel user) {
        return ResponseEntity.ok(dao.createUser(user));
    }
	
    @GetMapping("/getone")
    public ResponseEntity<Response> getUserById(@RequestParam Long Id) {
    	return ResponseEntity.ok(dao.getUserById(Id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<Response> getAllUsers() {
    	return ResponseEntity.ok(dao.getAllUsers());
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateUser(@RequestBody UserModel user) {       
        return ResponseEntity.ok(dao.updateUser(user));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Response> deleteUser(@PathVariable String userId) {
    	return ResponseEntity.ok(dao.deleteUser(userId));
    }

}
