package com.zomo.oa.service.imp;

import com.zomo.oa.pojo.User;
import com.zomo.oa.repository.UserRepository;
import com.zomo.oa.service.UserService;
import com.zomo.oa.util.Const;
import com.zomo.oa.util.MD5Utils;
import com.zomo.oa.util.ServiceResponse;
import com.zomo.oa.util.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ServiceResponse<User> register(User user) {

        User result = userRepository.findByUsername(user.getUsername());
        if (result != null)
            return ServiceResponse.createByErrorMsg("用户名已被注册");

        result = userRepository.findByEmail(user.getEmail());
        if (result != null)
            return ServiceResponse.createByErrorMsg("Email已被注册");

        result = userRepository.findByPhone(user.getPhone());
        if (result != null)
            return ServiceResponse.createByErrorMsg("手机号已被注册");

        user.setRole(Const.ROLE_DEFAULT);
        user.setPassword(MD5Utils.getMD5(user.getPassword()));

        result = userRepository.save(user);
        if (result == null)
            return ServiceResponse.createByErrorMsg("注册失败，请重试");

        return ServiceResponse.createBySuccessMsg("注册成功");
    }

    @Override
    public ServiceResponse<User> login(String username, String password) {
        String md5Password = MD5Utils.getMD5(password);
        User user = userRepository.findByUsernameAndPassword(username, md5Password);
        if (user == null)
            return ServiceResponse.createByErrorMsg("用户名或者密码错误请重试");
        return ServiceResponse.createBySuccess(user);
    }

    @Override
    public ServiceResponse<String> forget(String username, String email) {
        User user = userRepository.findByUsernameAndEmail(username, email);
        if (user == null)
            return ServiceResponse.createByErrorMsg("用户名或邮箱错误");

        return ServiceResponse.createBySuccess(user.getQuestion());
    }

    @Override
    public ServiceResponse<String> checkValid(String str, String type) {
        if (type.equals(Const.USERNAME)) {
            User user = userRepository.findByUsername(str);
            if (user != null)
                return ServiceResponse.createByErrorMsg("用户名已被注册");
        } else if (type.equals(Const.PHONE)) {
            User user = userRepository.findByPhone(str);
            if (user != null)
                return ServiceResponse.createByErrorMsg("手机号已被注册");
        } else if (type.equals(Const.EMAIL)) {
            User user = userRepository.findByEmail(str);
            if (user != null)
                return ServiceResponse.createByErrorMsg("邮箱已被注册");
        } else
            return ServiceResponse.createByErrorMsg("非法参数");

        return ServiceResponse.createBySuccess();
    }

    @Override
    public ServiceResponse<String> checkAnswer(String username, String question, String answer) {
        User user = userRepository.findByUsernameAndQuestionAndAnswer(username, question, answer);
        if (user == null)
            return ServiceResponse.createByErrorMsg("安全问题答案错误");
        String forgetToken = UUID.randomUUID().toString();
        TokenCache.setKey(Const.TOKEN + username, forgetToken);
        return ServiceResponse.createBySuccessMsg(forgetToken);
    }

    @Override
    public ServiceResponse<String> resetPasswordByToken(String username, String newPassword, String token) {
        if (StringUtils.isEmpty(token))
            return ServiceResponse.createByErrorMsg("参数错误，token不能为空");
        ServiceResponse validResponse = checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess())
            return ServiceResponse.createByErrorMsg("用户名错误");
        if (!token.equals(TokenCache.getValue(Const.TOKEN + username)))
            return ServiceResponse.createByErrorMsg("Token错误");
        User user = userRepository.findByUsername(username);
        if (user == null)
            return ServiceResponse.createByErrorMsg("错误");
        user.setPassword(MD5Utils.getMD5(newPassword));
        user = userRepository.save(user);
        if (user == null)
            return ServiceResponse.createByErrorMsg("更新数据错误");
        return ServiceResponse.createBySuccessMsg("更新密码成功");
    }

    @Override
    public ServiceResponse<String> resetPassword(String oldPassword, String newPassword, User user) {
        Integer id = user.getId();
        if (id == null)
            return ServiceResponse.createByErrorMsg("用户信息错误");
        user = userRepository.findByIdAndPassword(id, MD5Utils.getMD5(oldPassword));
        if (user == null)
            return ServiceResponse.createByErrorMsg("旧密码错误 ");
        user.setPassword(MD5Utils.getMD5(newPassword));
        user = userRepository.save(user);
        if (user == null)
            return ServiceResponse.createByErrorMsg("更新密码失败");
        return ServiceResponse.createBySuccessMsg("更新密码成功");
    }

    @Override
    public ServiceResponse<User> updateInfo(User user) {
        User result = userRepository.findByEmailAndIdNot(user.getEmail(), user.getId());
        if (result != null)
            return ServiceResponse.createByErrorMsg("邮箱已被占用");
        result = userRepository.findByPhoneAndIdNot(user.getPhone(), user.getId());
        if (result != null)
            return ServiceResponse.createByErrorMsg("手机号已被占用");
        result = userRepository.findOne(user.getId());
        if (result == null)
            return ServiceResponse.createByErrorMsg("更新错误");
        if (!StringUtils.isEmpty(user.getEmail()))
            result.setEmail(user.getEmail());
        if (!StringUtils.isEmpty(user.getPhone()))
            result.setEmail(user.getPhone());
        if (!StringUtils.isEmpty(user.getQuestion()))
            result.setEmail(user.getQuestion());
        if (!StringUtils.isEmpty(user.getAnswer()))
            result.setEmail(user.getAnswer());
        if (!StringUtils.isEmpty(user.getName()))
            result.setEmail(user.getName());
        result = userRepository.save(result);
        if (result == null)
            return ServiceResponse.createByErrorMsg("更新错误");
        return ServiceResponse.createBySuccessMsg("更新成功", result);
    }
}
