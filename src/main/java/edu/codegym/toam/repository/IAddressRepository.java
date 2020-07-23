package edu.codegym.toam.repository;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Addresses,Long> {
}
