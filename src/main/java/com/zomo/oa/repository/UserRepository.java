package com.zomo.oa.repository;

import com.zomo.oa.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

     User findByUsernameAndPassword(String username, String password);

     User findByUsername(String username);

     User findByEmail(String email);

     User findByPhone(String phone);

     User findByUsernameAndEmail(String username, String email);

     User findByUsernameAndQuestionAndAnswer(String username,String question,String answer);

     User findByIdAndPassword(Integer id,String password);

     User findByEmailAndIdNot(String email,Integer id);

     User findByPhoneAndIdNot(String phone,Integer id);


}
