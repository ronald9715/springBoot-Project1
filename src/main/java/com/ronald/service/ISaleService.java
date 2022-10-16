package com.ronald.service;

import com.ronald.dto.IProcedureDTO;
import com.ronald.dto.ProcedureDTO;
import com.ronald.model.Sale;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

//Indicamos la lista de Servicios u Operaciones que se va a realizar
//Extiende de la interfaz ICRUD que ya tiene todas las operaciones definidas para realizar el CRUD
public interface ISaleService extends ICRUD<Sale, Integer>{
    List<ProcedureDTO> callProcedure();

    List<ProcedureDTO> callProcedure2();

    List<IProcedureDTO> callProcedure3();
    List<IProcedureDTO> callProcedure4(Integer idClient);

    Sale getSaleMostExpensiveSale();

    Sale getLessExpensive();
    String getBestSalePerson();// El mejor vendedor
    Map<String, Long> getSalesCountBySeller();//Ventas por Vendedor

    Map<String, Double> getMostSellProduct();//Producto mas vendido


}
