package com.ronald.service.impl;

import com.ronald.model.User;
import com.ronald.repo.IGenericRepo;
import com.ronald.repo.IUserRepo;
import com.ronald.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {
    @Autowired
    private IUserRepo repo;
    @Override
    IGenericRepo<User, Integer> getRepo() {
        return repo;
    }
}
