package com.docker.service;

import com.docker.entities.User;

public interface IUserService {


    String saveUser(User user);
    User getUser(String userName);
}
