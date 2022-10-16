package com.ronald.service.impl;

import com.ronald.dto.IProcedureDTO;
import com.ronald.dto.ProcedureDTO;
import com.ronald.model.Sale;
import com.ronald.repo.IGenericRepo;
import com.ronald.repo.ISaleRepo;
import com.ronald.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {
    @Autowired
    private ISaleRepo repo;

    @Override
    IGenericRepo<Sale, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<ProcedureDTO> callProcedure() {
        List<ProcedureDTO> list = new ArrayList<>();
        repo.callProcedure().forEach(e -> {
            ProcedureDTO dto = new ProcedureDTO();
            dto.setQuantityFn((Integer) e[0]);
            dto.setDatetime((String) e[1]);
            list.add(dto);
        });

        return list;
    }

    @Override
    public List<ProcedureDTO> callProcedure2() {
        return repo.callProcedure2();
    }

    @Transactional
    @Override
    public List<IProcedureDTO> callProcedure3() {
        return repo.callProcedure3();
    }
    @Transactional
    @Override
    public List<IProcedureDTO> callProcedure4(Integer idClient) {
        return repo.callProcedure4(idClient);
    }

    @Override
    public Sale getSaleMostExpensiveSale() {
        //MAX y Comparar por Maximo
        Sale sale = repo.findAll().stream()
                .max(Comparator.comparing(Sale::getTotal))
                .orElse(new Sale());
        return sale;
    }

    @Override
    public Sale getLessExpensive() {
        Sale sale = repo.findAll().stream()
                .min(Comparator.comparing(Sale::getTotal))
                .orElse(new Sale());
        return sale;
    }

    @Override
    public String getBestSalePerson() {
         Map<String, Double> byUser = repo.findAll().stream()
                        .collect(Collectors.groupingBy(e->e.getUser().getUserName(), Collectors.summingDouble(e->e.getTotal())));
         String user = Collections.max(byUser.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();

        return user;
    }

    @Override
    public Map<String, Long> getSalesCountBySeller() {
        Map<String, Long> countByUser = repo.findAll().stream()
                        .collect(Collectors.groupingBy(e->e.getUser().getUserName(), Collectors.counting()));
        return countByUser;
    }

}


