package com.ronald.service.impl;

import com.ronald.model.Category;
import com.ronald.repo.ICategoryRepo;
import com.ronald.repo.IGenericRepo;
import com.ronald.service.ICRUD;
import com.ronald.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//Implementa a ICategoryService para decirle el tipo de Servicio que se va a realizar
//O sea de tipo Category
//Y Extiende de CruImpl ya que tiene todas las operaciones implementadas del CRUD
//Ademas tiene un metodo abstracto que debe implementarse
import java.util.List;
@Service
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {
    @Autowired
    private ICategoryRepo repo;

    @Override
    IGenericRepo<Category, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Category> findByName(String name) {
        List<Category> category = repo.findByNameContains(name);
        //Para que busque por comparacion
        //repo.findByNameLike("%"+ name + "%");
        return category;
    }

    @Override
    public List<Category> findByNameAndEnabled(String name, boolean enabled) {
        return repo.findByNameContainsAndEnabled(name, enabled);
    }

    @Override
    public List<Category> getByNameAndDescription1(String x, String y) {
        return repo.getByNameAndDescription1(x,y);
    }

    @Override
    public List<Category> getByNameAndDescription2(String x, String y) {
        return  repo.getByNameAndDescription2(x,y);
    }

    @Override
    public List<Category> getByNameAndDescription3() {
        return repo.getByNameAndDescription3();
    }

    @Override
    public Page<Category> findPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<Category> findAllOrder(String param) {
        Sort.Direction direction = param.equalsIgnoreCase("ASC")? Sort.Direction.ASC:Sort.Direction.DESC;
        return repo.findAll(Sort.by(direction,"name"));
    }
}
