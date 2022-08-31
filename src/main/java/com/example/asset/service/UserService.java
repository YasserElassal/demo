package com.example.asset.service;

import com.example.asset.model.User;
import com.example.asset.repository.ApiAccessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private ApiAccessImpl apiAccess;
    public User getOneUser(int id ){
        return  apiAccess.getOneUSer(id);

    }

    public void insertUser(User user ){
          apiAccess.insertUser(user);

    }

    public List<User> getAllUser(){
        return  apiAccess.getAllUSer();

    }
    public void updateUser(User user){

        apiAccess.updateUser( user);
    }

    public void deleteUser(int id){

        apiAccess.deleteUser( id);
    }

    public User getByPhone(int phone) {
        return apiAccess.getByPhone(phone);
    }


}
