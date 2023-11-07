package com.harshit.yourcartbackend.service;

import com.harshit.yourcartbackend.api.model.RegistrationBody;
import com.harshit.yourcartbackend.exception.UserAlreadyExistsException;
import com.harshit.yourcartbackend.model.LocalUser;
import com.harshit.yourcartbackend.model.dao.LocalUserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private LocalUserDAO localUserDAO;

    public UserService(LocalUserDAO localUserDAO) {
        this.localUserDAO = localUserDAO;
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
        //password encryption is due
        user.setPassword(registrationBody.getPassword());

        return localUserDAO.save(user);
    }
}
