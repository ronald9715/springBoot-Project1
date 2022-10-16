package com.ronald.controllers;

import com.ronald.dto.IProcedureDTO;
import com.ronald.dto.ProcedureDTO;
import com.ronald.dto.SaleDTO;
import com.ronald.exception.ModelNotFoundException;
import com.ronald.model.Sale;
import com.ronald.service.ISaleService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private ISaleService service;
    @Autowired
    @Qualifier("saleMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> readAll() throws Exception{
        List<SaleDTO> list = service.readAll().stream().map(sale -> mapper.map(sale, SaleDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Sale obj = service.readById(id);
        if (obj== null){
            throw new ModelNotFoundException("ID NOT FOUND: "+id);
        }
        return new ResponseEntity<>(mapper.map(obj, SaleDTO.class), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<SaleDTO> create(@RequestBody SaleDTO dto) throws Exception{
        Sale sale = service.create(mapper.map(dto,Sale.class));
        return new ResponseEntity<>(mapper.map(sale, SaleDTO.class), HttpStatus.CREATED);
    }
    @PutMapping()
    public ResponseEntity<SaleDTO> update(@RequestBody SaleDTO dto) throws Exception{
        Sale obj= service.readById(dto.getIdSale());
        if(obj==null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ dto.getIdSale());
        }
        Sale sale = service.update(mapper.map(dto, Sale.class));
        return new ResponseEntity<>(mapper.map(sale, SaleDTO.class), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Sale obj = service.readById(id);
        if(obj== null){
            throw new ModelNotFoundException("RESOURCE NOT FOUND: "+id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //////////////Call Procedure//////////////////////
    @GetMapping("/resume")
    public ResponseEntity<List<ProcedureDTO>> callProcedure() throws Exception{
        List<ProcedureDTO> list = service.callProcedure();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/resume2")
    public ResponseEntity<List<ProcedureDTO>> callProcedure2() throws Exception{
        List<ProcedureDTO> list = service.callProcedure2();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/resume3")
    public ResponseEntity<List<IProcedureDTO>> callProcedure3() throws Exception{
        return new ResponseEntity<>(service.callProcedure3(), HttpStatus.OK);
    }
    @GetMapping("/resume4/{id}")
    public ResponseEntity<List<IProcedureDTO>> callProcedure4(@PathVariable("id") Integer idClient) throws Exception{
        List<IProcedureDTO> list = service.callProcedure4(idClient);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/mostexpensive")
    public ResponseEntity<SaleDTO> mostExpensive() throws Exception{
        Sale sale = service.getSaleMostExpensiveSale();
        return new ResponseEntity<>(mapper.map(sale, SaleDTO.class), HttpStatus.OK);
    }

    @GetMapping("/lessexpensive")
    public ResponseEntity<SaleDTO> lessExpensive() throws Exception{
        Sale sale = service.getLessExpensive();
        return new ResponseEntity<>(mapper.map(sale, SaleDTO.class), HttpStatus.OK);
    }


}
