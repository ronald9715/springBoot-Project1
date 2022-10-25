package com.ronald.service;

import com.ronald.model.Category;
import com.ronald.repo.ICategoryRepo;
import com.ronald.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//Se utiliza esta notacion para Testear Service
@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {
    @InjectMocks
    private CategoryServiceImpl service;
    @MockBean
    private ICategoryRepo repo;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        Category category_1 = new Category(1,"TV", "Televisions", true);
        Category category_2 = new Category(2,"PSP", "Play Station", true);
        Category category_3 = new Category(3,"Books", "Some books", true);
        List<Category> categories = Arrays.asList(category_1,category_2,category_3);
        Mockito.when(repo.findAll()).thenReturn(categories);
        Mockito.when(repo.findById(any())).thenReturn(Optional.of(category_1));
        Mockito.when(repo.save(any())).thenReturn(category_2);
    }

    @Test
    public void readAll() throws Exception{
        List<Category> response = service.readAll();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(),3);
    }
    @Test
    public void readById() throws Exception{
        Category response = service.readById(any());
        assertNotNull(response);
    }
    @Test
    public void create() throws Exception{
        Category response = service.create(any());
        assertNotNull(response);
    }
    @Test
    public void update() throws Exception{
        Category response = service.update(any());
        assertNotNull(response);

    }
    @Test
    public void delete() throws Exception{
        service.delete(1);
        verify(repo, times(1)).deleteById(1);
    }

}
