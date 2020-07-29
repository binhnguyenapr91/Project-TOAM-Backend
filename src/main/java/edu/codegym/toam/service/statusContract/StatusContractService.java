package edu.codegym.toam.service.statusContract;

import edu.codegym.toam.model.ContractStatus;
import edu.codegym.toam.repository.StatusContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusContractService implements IStatusContractService {
    @Autowired
    StatusContractRepository statusContractRepository;

    @Override
    public Iterable<ContractStatus> findAll() {
        return statusContractRepository.findAll();
    }

    @Override
    public ContractStatus findById(Long id) {
        return statusContractRepository.findById(id).get();
    }

    @Override
    public ContractStatus update(ContractStatus status) {
        return statusContractRepository.save(status);
    }

    @Override
    public void removeById(Long id) {
        statusContractRepository.deleteById(id);
    }

    @Override
    public ContractStatus create(ContractStatus status) {
        return statusContractRepository.save(status);
    }
}
