package com.ronald.service;

import com.ronald.model.Category;

import java.util.List;

//Indicamos la lista de Servicios u Operaciones que se va a realizar
//Extiende de la interfaz ICRUD que ya tiene todas las operaciones definidas para realizar el CRUD
public interface ICategoryService extends ICRUD<Category, Integer>{
    List<Category> findByName(String name);
    List<Category> findByNameAndEnabled(String name, boolean enabled);
    List<Category> getByNameAndDescription1(String name, String description);
    List<Category> getByNameAndDescription2(String name, String description);
    List<Category> getByNameAndDescription3();
}
