package com.example.bankmanagementsystem.Repository;

import com.example.bankmanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {
    User findUserByUserName(String userName);
    //  User findUserByUserNameAndPassword(String userName, String password);
    User findUserById(Integer id);

}
