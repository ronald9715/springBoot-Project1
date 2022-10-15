package com.ronald.repo;

import com.ronald.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepo extends IGenericRepo<Product, Integer> {
}
