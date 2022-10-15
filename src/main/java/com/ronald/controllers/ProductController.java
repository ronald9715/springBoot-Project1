package com.ronald.controllers;

import com.ronald.dto.CategoryDTO;
import com.ronald.dto.ProductDTO;
import com.ronald.exception.ModelNotFoundException;
import com.ronald.model.Category;
import com.ronald.model.Product;
import com.ronald.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")// Sirve para exponer nuesto servicio a traves de un endpoint
public class ProductController {
    //Spring busca una implementacion de esta Interfaz
    @Autowired
    private IProductService service;
    @Autowired
    @Qualifier("productMapper")
    private ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAll() throws Exception{
        List<ProductDTO> list = service.readAll().stream().map(prod -> mapper.map(prod, ProductDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //PathVariable, se utiliza para recuperar el dato pasado de la URL
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Product obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ id);
        }
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) throws Exception{
        Product pd = service.create(mapper.map(dto, Product.class));
        return new ResponseEntity<>(mapper.map(pd, ProductDTO.class) , HttpStatus.CREATED);
    }
    //Se utiliza RequestBody para capturar el Json enviado enviado en la peticion
    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO dto) throws Exception{
        Product obj = service.readById(dto.getIdProduct()) ;
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ dto.getIdProduct());
        }
        Product pd = service.update(mapper.map(dto, Product.class));
        return new ResponseEntity<>(mapper.map(pd, ProductDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Product pd = service.readById(id);
        if(pd == null){
            throw new ModelNotFoundException("RESOURCE NOT FOUND: "+ id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
