package com.ronald.repo;

import com.ronald.dto.IProcedureDTO;
import com.ronald.dto.ProcedureDTO;
import com.ronald.model.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISaleRepo extends IGenericRepo<Sale, Integer>{

    //Invocando un Procedimiento de Forma Nativa
    @Query(value = "select * from fn_sales()", nativeQuery = true)
    List<Object[]> callProcedure();
    @Query(nativeQuery = true, name = "Sale.fn_sales")
    List<ProcedureDTO> callProcedure2();

    @Procedure(procedureName = "fn_sales")
    List<IProcedureDTO> callProcedure3();

    @Procedure(procedureName = "fn_salesParameter2")
    List<IProcedureDTO> callProcedure4(@Param("p_id_client") Integer idClient);

}
