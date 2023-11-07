package com.harshit.yourcartbackend.model.dao;

import com.harshit.yourcartbackend.model.LocalUser;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface LocalUserDAO extends CrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByEmailIgnoreCase(String email);
    Optional<LocalUser> findByUsernameIgnoreCase(String username);
}
