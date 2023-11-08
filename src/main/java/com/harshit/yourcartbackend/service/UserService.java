package com.harshit.yourcartbackend.service;

import com.harshit.yourcartbackend.api.model.LoginBody;
import com.harshit.yourcartbackend.api.model.RegistrationBody;
import com.harshit.yourcartbackend.exception.UserAlreadyExistsException;
import com.harshit.yourcartbackend.model.LocalUser;
import com.harshit.yourcartbackend.model.dao.LocalUserDAO;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private LocalUserDAO localUserDAO;
    private EncryptionService encryptionService;
    private JWTService jwtService;

    public UserService(LocalUserDAO localUserDAO, EncryptionService encryptionService, JWTService jwtService) {
        this.localUserDAO = localUserDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }


    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if (localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent() || localUserDAO.findByUsernameIgnoreCase(registrationBody.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        LocalUser user = new LocalUser();
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));

        return localUserDAO.save(user);
    }

    public String loginUser(LoginBody loginBody) {
        Optional<LocalUser> optUser = localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());

        if (optUser.isPresent()) {
            LocalUser user = optUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }

        return null;
    }
}
