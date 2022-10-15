package com.ronald.controllers;

import com.ronald.dto.CategoryDTO;
import com.ronald.exception.ModelNotFoundException;
import com.ronald.model.Category;
import com.ronald.service.ICategoryService;
import com.ronald.service.impl.CategoryServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService service;
    @Autowired
    @Qualifier("categoryMapper")
    private ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<CategoryDTO> > readAll() throws Exception{
        List<CategoryDTO> list = service.readAll().stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO>  readById(@PathVariable("id") Integer id) throws Exception{
        Category obj = service.readById(id);
        if(obj== null){
            throw  new ModelNotFoundException("ID NOT FOUND: "+ id);
        }
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class) , HttpStatus.OK) ;
    }
    //Se utiliza @RequestBody para que haya una compatabilidad entre el JSON que
    //se manda y la clase categoria
    @PostMapping
    public ResponseEntity<CategoryDTO>  create(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.create(mapper.map(dto, Category.class) );
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class), HttpStatus.CREATED) ;
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.readById(dto.getId());
        System.out.println(obj.toString());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ dto.getId());
        }
        Category cat = service.update(mapper.map(dto, Category.class));
        return new ResponseEntity<>(mapper.map(cat,CategoryDTO.class) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  delete(@PathVariable("id") Integer id) throws Exception{
        Category obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("RESOURCE NOT FOUND: "+id);
        }
        service.delete(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    ///////////queries/////////////////////
    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable("name") String name) throws Exception{
        List<CategoryDTO> list = service.findByName(name.toUpperCase()).stream().map(category -> mapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/find/name")
    public ResponseEntity<List<CategoryDTO>> findByNameAndEnabled(@RequestParam("name") String name,@RequestParam("enabled") boolean enabled) throws Exception{
        List<CategoryDTO> list = service.findByNameAndEnabled(name.toUpperCase(), enabled).stream().map(category -> mapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/get/name/description")
    public ResponseEntity<List<CategoryDTO>> getByNameAndDescription() throws Exception{
        List<CategoryDTO> list = service.getByNameAndDescription1("COMPUTERS","To many").stream().map(category -> mapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/get/name/description2")
    public ResponseEntity<List<CategoryDTO>> getByNameAndDescription2(@RequestParam("name") String x, @RequestParam("description")String y) throws Exception{
        List<CategoryDTO> list = service.getByNameAndDescription2(x,y).stream().map(category -> mapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/get/name/description3")
    public ResponseEntity<List<CategoryDTO>> getByNameAndDescription3() throws Exception{
        List<CategoryDTO> list = service.getByNameAndDescription3().stream().map(category -> mapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
