package com.ronald.service.impl;

import com.ronald.model.Provider;
import com.ronald.repo.IGenericRepo;
import com.ronald.repo.IProviderRepo;
import com.ronald.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderImpl extends CRUDImpl<Provider, Integer> implements IProviderService {
    @Autowired
    private IProviderRepo repo;
    @Override
    IGenericRepo<Provider, Integer> getRepo() {
        return repo;
    }
}
