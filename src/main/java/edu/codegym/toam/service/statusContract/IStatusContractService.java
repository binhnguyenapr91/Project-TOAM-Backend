package edu.codegym.toam.service.statusContract;

import edu.codegym.toam.model.Status;

public interface IStatusContractService {
    Iterable<Status> findAll();
    Status findById(Long id);
    Status update(Status status);
    void removeById(Long id);
    Status create(Status status);
}
