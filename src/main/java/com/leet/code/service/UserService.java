package com.leet.code.service;

import org.springframework.stereotype.Service;
import com.leet.code.model.Response;
import com.leet.code.model.UserModel;

@Service
public interface UserService {

	Response createUser(UserModel user);

	Response getUserById(Long userId);

	Response getAllUsers();

    Response updateUser(UserModel user);

	Response deleteUser(String userId);

}
