package com.darius.services;

import com.darius.models.User;
import com.darius.repository.AddressRepository;
import com.darius.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepo;
    private AddressRepository addressRepo; // the save() method

    @Autowired
    public UserService(UserRepository userRepo, AddressRepository addressRepo) {
        super();
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
    }

    // return a set of all users in the DB

    @Transactional(readOnly=true)
    public Set<User> getAll() {

        return userRepo.findAll().stream().collect(Collectors.toSet());
    }

    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public User add(User u) {

        // if the address set is NOT null, iterate over each address and .save() them
        if (u.getAddresses() != null) {
            u.getAddresses().forEach(a -> addressRepo.save(a));
        }

        return userRepo.save(u);
    }



}


