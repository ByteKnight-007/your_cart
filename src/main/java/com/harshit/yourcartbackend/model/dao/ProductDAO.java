package com.harshit.yourcartbackend.model.dao;

import com.harshit.yourcartbackend.model.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductDAO extends ListCrudRepository<Product, Long> {
}
