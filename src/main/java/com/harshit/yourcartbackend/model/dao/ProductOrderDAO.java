package com.harshit.yourcartbackend.model.dao;

import com.harshit.yourcartbackend.model.LocalUser;
import com.harshit.yourcartbackend.model.ProductOrder;
import org.springframework.data.repository.ListCrudRepository;
import java.util.List;

public interface ProductOrderDAO extends ListCrudRepository<ProductOrder, Long> {
    List<ProductOrder> findByUser(LocalUser user);
}
