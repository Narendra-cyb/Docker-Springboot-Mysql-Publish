package com.docker.service.impl;

import com.docker.entities.User;
import com.docker.repository.IUserRepo;
import com.docker.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepo userRepo;
    @Override
    public String saveUser(User user) {
        User save = userRepo.save(user);
        if(save!=null) {
            return "user saved succesfully: "+save.getName();
        }
        else{
            return "error while saving";
        }
    }

    @Override
    public User getUser(String userName) {
        User name = userRepo.findByName(userName);
        return name;

    }
}
