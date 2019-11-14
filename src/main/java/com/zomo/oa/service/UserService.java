package com.zomo.oa.service;

import com.zomo.oa.pojo.User;
import com.zomo.oa.util.ServiceResponse;
import org.springframework.stereotype.Service;

public interface UserService {

    ServiceResponse<User> register(User user);

    ServiceResponse<User> login(String username,String password);

    ServiceResponse<String> forget(String username,String email);

    ServiceResponse<String> checkValid(String str,String type);

    ServiceResponse<String> checkAnswer(String username,String question,String answer);

    ServiceResponse<String> resetPasswordByToken(String username, String newPassword, String token);

    ServiceResponse<String> resetPassword(String oldPassword, String newPassword,User user);

    ServiceResponse<User> updateInfo(User user);

}
