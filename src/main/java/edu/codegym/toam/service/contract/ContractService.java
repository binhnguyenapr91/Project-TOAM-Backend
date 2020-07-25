package edu.codegym.toam.service.contract;

import edu.codegym.toam.model.Contracts;
import edu.codegym.toam.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService implements IContractService {
    @Autowired
    ContractRepository contractRepository;

    @Override
    public Iterable<Contracts> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contracts findById(Long id) {
        return contractRepository.findById(id).get();
    }

    @Override
    public Contracts update(Contracts contracts) {
        return contractRepository.save(contracts);
    }

    @Override
    public void removeById(Long id) {
        contractRepository.deleteById(id);
    }

    @Override
    public Contracts create(Contracts contracts) {
        return contractRepository.save(contracts);
    }

    @Override
    public Iterable<Contracts> findAllContractsByHostId(Long id) {
        return contractRepository.findAllContractsByHostId(id);
    }

    @Override
    public Iterable<Contracts> findAllContractsByRenterId(Long id) {
        return contractRepository.findAllContractsByRenterId(id);
    }
}
