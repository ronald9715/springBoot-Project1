package com.ronald.controllers;

import com.ronald.dto.ProviderDTO;
import com.ronald.exception.ModelNotFoundException;
import com.ronald.model.Provider;
import com.ronald.service.IProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/providers")
public class ProviderController {
    @Autowired
    private IProviderService service;
    @Autowired
    @Qualifier("providerMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> readAll() throws Exception{
        List<ProviderDTO> provider = service.readAll().stream().map(provider1 -> mapper.map(provider1, ProviderDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(provider, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Provider provider = service.readById(id);
        if(provider == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ id);
        }
        return new ResponseEntity<>(mapper.map(provider, ProviderDTO.class), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProviderDTO> create(@RequestBody ProviderDTO dto) throws Exception{
        Provider provider = service.create(mapper.map(dto, Provider.class));
        return new ResponseEntity<>(mapper.map(provider, ProviderDTO.class), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<ProviderDTO> update(@RequestBody ProviderDTO dto) throws Exception{
        Provider obj = service.readById(dto.getIdProvider());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ dto.getIdProvider());
        }
        Provider provider = service.update(mapper.map(dto, Provider.class));
        return new ResponseEntity<>(mapper.map(provider, ProviderDTO.class), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Provider obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("RESOURCE NOT FOUND: "+ id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
