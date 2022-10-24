package com.ronald.controller;

import com.ronald.controllers.CategoryController;
import com.ronald.dto.CategoryDTO;
import com.ronald.model.Category;
import com.ronald.service.ICategoryService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//Especificar sobre que controlador vamos a hacer las pruebas
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ICategoryService service;
    @MockBean(name = "categoryMapper")
    private ModelMapper mapper;

    Category category_1 = new Category(1,"TV", "Televisions", true);
    Category category_2 = new Category(2,"PSP", "Play Station", true);
    Category category_3 = new Category(3,"Books", "Some books", true);

    CategoryDTO categorydto_1 = new CategoryDTO(1,"DVD", "Disc Player", true);
    CategoryDTO categorydto_2 = new CategoryDTO(2,"PSP", "Play Station Portatil", true);
    CategoryDTO categorydto_3 = new CategoryDTO(3,"Books", "Some books", true);

    @Test
    public void readAllTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/categories")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }
}
