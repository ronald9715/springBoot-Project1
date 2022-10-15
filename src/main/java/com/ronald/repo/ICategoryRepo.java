package com.ronald.repo;

import com.ronald.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepo extends IGenericRepo<Category, Integer> {
    //DerivedQueries === QueriesDerivados
    List<Category> findByName(String name);
    List<Category> findByNameLike(String name);
    List<Category> findByNameContains(String name);
    //%XYZ% FindByNameContains
    //%XYZ FindByNameStartsWith
    //XYZ% FindByNameEndsWith
    List<Category> findByNameContainsAndEnabled(String name, boolean enabled);

    //findByEnabledTrue
    //findByEnabledFalse
    //Category findOneByName(String name)
    //List<Category> findTop3ByName(String name)
    //List<Category> findByNameIs(String name)
    //List<Category> findByNameIsNot(String name)
    //List<Category> findByNameIsNull(String name)
    //List<Category> findByNameIsNotNull(String name)
    //List<Category> findByNameEquals(String name)
    //List<Category> findByNameEqualsIgnoreCase(String name)
    //List<Category> findByIdCategoryLessThan(String name)
    //List<Category> findByIdCategoryGreaterThanEqual(String name)
    //List<Category> findByIdCategoryGreaterThanEqual(String name)
    //List<Category> findByIdCategoryBetween(Integer id)
    //List<Category> findByNameOrderByDescription(String name)
    //List<Category> findByNameOrderByDescriptionAsc(String name)

    //JPQL
    @Query("FROM Category c WHERE c.name = ?1 AND c.description like %?2%")
    List<Category> getByNameAndDescription1(String name, String description);

    @Query("FROM Category c WHERE c.name = :name AND c.description like %:description%")
    List<Category> getByNameAndDescription2(@Param("name") String x,@Param("description") String y);

    @Query("SELECT new Category (c.name, c.description) FROM Category c")
    List<Category> getByNameAndDescription3();
    ////////////////////////////////////////
    //SQL Querys Nativos
    //Escribes las consulta tal cual lo ejecutarias en tu motor de base de datos
    @Query(value = "SELECT * from category where name = :name", nativeQuery = true)
    List<Category> getByNameSQL(@Param("name") String name);

    @Modifying //Para Insert, Update, Delete
    @Query(value = "UPDATE category set name= :name", nativeQuery = true)
    Integer updateNames(@Param("name") String name);


}
