package edu.codegym.toam.repository;

import edu.codegym.toam.model.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Addresses,Long> {
    @Query(value = "SELECT a.id FROM Addresses a WHERE a.id NOT IN (SELECT p.addresses.id FROM Properties p )"
    )
    Iterable<Long> findNewAddress();
}

