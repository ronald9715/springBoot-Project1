package com.ronald.controllers;

import com.ronald.dto.ClientDTO;
import com.ronald.exception.ModelNotFoundException;
import com.ronald.model.Client;
import com.ronald.repo.IClientRepo;
import com.ronald.service.IClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private IClientService service;
    @Autowired
    @Qualifier("clientMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> readAll() throws Exception{
        List<ClientDTO> list = service.readAll().stream().map(clt->mapper.map(clt, ClientDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Client obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ id);
        }
        ClientDTO dto = mapper.map(obj, ClientDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO dto) throws Exception{
        Client clt = service.create(mapper.map(dto, Client.class));
        return new ResponseEntity<>(mapper.map(clt, ClientDTO.class), HttpStatus.CREATED);
    }
    //Ya debemos saber el ID
    @PutMapping()
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO dto) throws Exception{
        Client clt = service.readById(dto.getIdClient());
        if (clt == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ dto.getIdClient());
        }
        return new ResponseEntity<>(mapper.map(clt, ClientDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Client clt = service.readById(id);
        if (clt == null){
            throw new ModelNotFoundException("RESOURCE NOT FOUND: "+ id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
