package edu.codegym.toam.service.statusContract;

import edu.codegym.toam.model.Status;
import edu.codegym.toam.repository.StatusContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusContractService implements IStatusContractService {
    @Autowired
    StatusContractRepository statusContractRepository;

    @Override
    public Iterable<Status> findAll() {
        return statusContractRepository.findAll();
    }

    @Override
    public Status findById(Long id) {
        return statusContractRepository.findById(id).get();
    }

    @Override
    public Status update(Status status) {
        return statusContractRepository.save(status);
    }

    @Override
    public void removeById(Long id) {
        statusContractRepository.deleteById(id);
    }

    @Override
    public Status create(Status status) {
        return statusContractRepository.save(status);
    }
}
