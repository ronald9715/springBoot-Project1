package com.ronald.repo;

import com.ronald.model.User;

public interface IUserRepo extends IGenericRepo<User, Integer>{
    User findOneByUserName(String username);
}
