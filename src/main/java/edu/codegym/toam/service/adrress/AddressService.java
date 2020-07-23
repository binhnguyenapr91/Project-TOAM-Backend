package edu.codegym.toam.service.adrress;

import edu.codegym.toam.model.Addresses;
import edu.codegym.toam.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Iterable<Addresses> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Addresses findById(Long id) {
        return addressRepository.findById(id).get();
    }


    @Override
    public Addresses update(Addresses addresses) {
        return addressRepository.save(addresses);
    }

    @Override
    public void removeById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Addresses create(Addresses addresses) {
        return addressRepository.save(addresses);
    }
}
