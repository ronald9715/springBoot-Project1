package com.ronald.service.impl;

import com.ronald.model.Role;
import com.ronald.repo.IGenericRepo;
import com.ronald.repo.IRoleRepo;
import com.ronald.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRoleService {
    @Autowired
    private IRoleRepo repo;
    @Override
    IGenericRepo<Role, Integer> getRepo() {
        return repo;
    }
}
