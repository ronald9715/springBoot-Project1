package com.ronald.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
//Se crea una interfaz generica para implementar los repos
//Se extiende de la interfaz Jpa Repository que tiene todos los metodos para hacer el CRUD
//Adicionalmente crea automaticamente un Bean del tipo de Repo
@NoRepositoryBean
public interface IGenericRepo<T, ID> extends JpaRepository<T, ID> {
}
