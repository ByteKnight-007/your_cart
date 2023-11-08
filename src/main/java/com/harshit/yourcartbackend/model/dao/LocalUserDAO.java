package com.harshit.yourcartbackend.model.dao;

import com.harshit.yourcartbackend.model.LocalUser;
import org.springframework.data.repository.ListCrudRepository;
import java.util.Optional;

public interface LocalUserDAO extends ListCrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByEmailIgnoreCase(String email);
    Optional<LocalUser> findByUsernameIgnoreCase(String username);
}
