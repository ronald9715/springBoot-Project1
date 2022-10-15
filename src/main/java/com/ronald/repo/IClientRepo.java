package com.ronald.repo;

import com.ronald.model.Category;
import com.ronald.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepo extends IGenericRepo<Client, Integer> {
}
