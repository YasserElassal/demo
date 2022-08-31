package com.example.asset.repository;


import com.example.asset.exceptions.UserNotFoundException;
import com.example.asset.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class ApiAccessImpl implements ApiAccess {
    private static final Logger mylog = LogManager.getLogger(ApiAccessImpl.class);
    RestTemplate restTemplate = new RestTemplate();
    @Value("${getUser}")
    private String userUri;
    @Value("${searchByPhone}")
    private String userUriPhone ;

    @Override
    public User getOneUSer(int id) {

        User user;
        try {
            user = restTemplate.getForObject(userUri + id, User.class);

        } catch (Exception e) {
            throw new UserNotFoundException(String.valueOf(id));
        }

        return user;
    }


    @Override
    public void insertUser(User user) {
        try {
            User insertedUser = restTemplate.postForObject(userUri, user, User.class);

            mylog.info("user with id [" + user.getId() + "] inserted ");

        } finally {

        }


    }

    @Override
    public List<User> getAllUSer() {

        List<User> users;

        try {


            ResponseEntity<List<User>> responseEntity = restTemplate.exchange(userUri, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<User>>() {
            });
            users = responseEntity.getBody();


        } finally {

        }

        return users;
    }

    @Override
    public void updateUser(User user) {

        try {
            restTemplate.put(userUri, user);
            mylog.info("user with id [" + user.getId() + "] updated  ");
        } finally {

        }
    }

    @Override
    public void deleteUser(int id) {

        try {
            restTemplate.delete(userUri + id, id);
            mylog.info("user with id [" + id + "] deleted ");
        }

        catch (HttpClientErrorException.NotFound e ){
            throw new UserNotFoundException(String.valueOf(id));


        }
    }

    @Override
    public User getByPhone(int phone) {

        User user;
        try {
            user =restTemplate.getForObject(userUriPhone+phone , User.class);

        } catch (Exception e) {

            throw new UserNotFoundException(e);
        }

        return user;

    }


}
