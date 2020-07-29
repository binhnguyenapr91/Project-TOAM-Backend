package edu.codegym.toam.service.adrress;

import edu.codegym.toam.model.Addresses;
import edu.codegym.toam.model.Addresses;

public interface IAddressService {
    Iterable<Addresses> findAll();
    Addresses findById(Long id);
    Addresses update(Addresses addresses);
    void removeById(Long id);
    Addresses create(Addresses addresses);
}
