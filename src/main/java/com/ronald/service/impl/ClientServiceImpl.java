package com.ronald.service.impl;

import com.ronald.model.Client;
import com.ronald.repo.IClientRepo;
import com.ronald.repo.IGenericRepo;
import com.ronald.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService {
    @Autowired
    private IClientRepo repo;

    @Override
    IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }


}
