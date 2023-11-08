package com.harshit.yourcartbackend.service;

import com.harshit.yourcartbackend.model.Product;
import com.harshit.yourcartbackend.model.dao.ProductDAO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private ProductDAO productDAO;
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getProducts() {
        return productDAO.findAll();
    }
}
