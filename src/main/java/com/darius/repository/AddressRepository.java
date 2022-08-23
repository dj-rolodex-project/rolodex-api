package com.darius.repository;

import com.darius.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

    // all the basic CRUD methods are abstracted away from us .save(), .findById()....

}

