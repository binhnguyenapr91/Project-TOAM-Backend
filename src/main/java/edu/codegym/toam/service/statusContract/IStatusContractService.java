package edu.codegym.toam.service.statusContract;

import edu.codegym.toam.model.ContractStatus;

public interface IStatusContractService {
    Iterable<ContractStatus> findAll();
    ContractStatus findById(Long id);
    ContractStatus update(ContractStatus status);
    void removeById(Long id);
    ContractStatus create(ContractStatus status);
}
