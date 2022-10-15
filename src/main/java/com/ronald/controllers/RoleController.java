package com.ronald.controllers;

import com.ronald.dto.RoleDTO;
import com.ronald.exception.ModelNotFoundException;
import com.ronald.model.Role;
import com.ronald.service.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private IRoleService service;
    @Autowired
    @Qualifier("roleMapper")
    private ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<RoleDTO>> readAll() throws Exception{
        List<RoleDTO> list = service.readAll().stream().map(role -> mapper.map(role, RoleDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Role role = service.readById(id);
        if(role == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ id);
        }
        return new ResponseEntity<>(mapper.map(role, RoleDTO.class), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<RoleDTO> create(@RequestBody RoleDTO dto) throws Exception{
        Role role = service.create(mapper.map(dto, Role.class));
        return new ResponseEntity<>(mapper.map(role, RoleDTO.class), HttpStatus.CREATED);
    }
    @PutMapping()
    public ResponseEntity<RoleDTO> update(@RequestBody RoleDTO dto) throws Exception{
        Role obj = service.readById(dto.getIdRole());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ dto.getIdRole());
        }
        Role role = service.update(mapper.map(dto, Role.class));
        return new ResponseEntity<>(mapper.map(role, RoleDTO.class),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Role obj = service.readById(id);
        if (obj == null){
            throw new ModelNotFoundException("RESOURCE NOT FOUND: "+ id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
