package com.ronald.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ronald.controllers.CategoryController;
import com.ronald.dto.CategoryDTO;
import com.ronald.exception.ModelNotFoundException;
import com.ronald.model.Category;
import com.ronald.service.ICategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//Especificar sobre que controlador vamos a hacer las pruebas
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
    @Autowired
    // Se utiliza mockMvc para testear Controladores simulando
    //la realizacion de llamadas HTTP
    private MockMvc mockMvc;
    @MockBean
    private ICategoryService service;
    @MockBean(name = "categoryMapper")
    private ModelMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;
    Category category_1 = new Category(1,"TV", "Televisions", true);
    Category category_2 = new Category(2,"PSP", "Play Station", true);
    Category category_3 = new Category(3,"Books", "Some books", true);

    CategoryDTO categorydto_1 = new CategoryDTO(1,"DVD", "Disc Player", true);
    CategoryDTO categorydto_2 = new CategoryDTO(2,"PSP", "Play Station Portatil", true);
    CategoryDTO categorydto_3 = new CategoryDTO(3,"Books", "Some books", true);

    //GET (READ ALL)
    @Test
    public void readAllTest() throws Exception{
        List<Category> categories = Arrays.asList(category_1,category_2,category_3);
        Mockito.when(service.readAll()).thenReturn(categories);
        Mockito.when(mapper.map(category_1, CategoryDTO.class)).thenReturn(categorydto_1);
        Mockito.when(mapper.map(category_2, CategoryDTO.class)).thenReturn(categorydto_2);
        Mockito.when(mapper.map(category_3, CategoryDTO.class)).thenReturn(categorydto_3);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/categories")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].nameCategory", is("PSP")));

    }
    //POST
    @Test
    public void createTest() throws Exception{
        Mockito.when(service.create(any())).thenReturn(category_1);
        Mockito.when(mapper.map(category_1, CategoryDTO.class)).thenReturn(categorydto_1);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categorydto_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect( jsonPath("$.nameCategory",is("DVD")))
                .andExpect(jsonPath("$.enabledCategory", is(true)));



    }
    @Test
    public void updateTest() throws Exception{
        //Recibe Peticion POST
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categorydto_2));
        //Comprueba que exista el modelo y luego retorna un DTO
        Mockito.when(service.readById(any())).thenReturn(category_1);
        Mockito.when(service.update(any())).thenReturn(category_2);
        Mockito.when(mapper.map(category_2, CategoryDTO.class)).thenReturn(categorydto_2);
        //Los resultados que se esperan
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameCategory",is("PSP")))
                .andExpect(jsonPath("$.enabledCategory", is(true)));
    }
    @Test
    public void updateErrorTest() throws Exception{
        Mockito.when(service.readById(any())).thenReturn(null);
        //RECIBE EN EL POSTMAN UN DTO
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categorydto_2));


        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ModelNotFoundException));

    }
    @Test
    public void deleteTest() throws Exception{
        int id_category =1;
        Mockito.when(service.readById(any())).thenReturn(category_1);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/categories/"+id_category)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isNoContent());
    }
    @Test
    public void deleteErrorTest() throws Exception{
        int id_category =999;
        Mockito.when(service.readById(any())).thenReturn(null);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/categories/"+id_category)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ModelNotFoundException));
    }
}
