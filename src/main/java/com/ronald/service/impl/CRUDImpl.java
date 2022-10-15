package com.ronald.service.impl;

import com.ronald.repo.IGenericRepo;
import com.ronald.service.ICRUD;

import java.util.List;
//Los metodos: save(), findAll(), findById() y deleteById() pertenecen a la clase JpaRepository
//que utilizamos en la capa Repo
public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {
    //Creamos un metodo que nos pueda devolver el tipo
    //de Repo segun el servicio
    abstract IGenericRepo<T, ID> getRepo();
    @Override
    public T create(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T readById(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
