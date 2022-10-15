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
import java.util.ArrayList;
import java.util.List;

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
}


