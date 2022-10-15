package com.ronald.service;

import com.ronald.model.Category;

import java.util.List;

//Define el contrato de todas las operaciones que deben realizar las clases que lo implementan
public interface ICRUD <T, ID>{
    T create(T t) throws Exception;
    T update(T t) throws Exception;
    List<T> readAll() throws Exception;
    T readById(ID id) throws Exception;
    void delete(ID id) throws Exception;
}
