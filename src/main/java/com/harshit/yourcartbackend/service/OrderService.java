package com.harshit.yourcartbackend.service;

import com.harshit.yourcartbackend.model.LocalUser;
import com.harshit.yourcartbackend.model.ProductOrder;
import com.harshit.yourcartbackend.model.dao.ProductOrderDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private ProductOrderDAO productOrderDAO;
    public OrderService(ProductOrderDAO productOrderDAO) {
        this.productOrderDAO = productOrderDAO;
    }

    public List<ProductOrder> getOrders(LocalUser user) {
        return productOrderDAO.findByUser(user);
    }

}
