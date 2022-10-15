package com.ronald.service.impl;

import com.ronald.model.Product;
import com.ronald.repo.IGenericRepo;
import com.ronald.repo.IProductRepo;
import com.ronald.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService{
    @Autowired
    private IProductRepo repo;
    @Override
    IGenericRepo<Product, Integer> getRepo() {
        return repo;
    }
}
