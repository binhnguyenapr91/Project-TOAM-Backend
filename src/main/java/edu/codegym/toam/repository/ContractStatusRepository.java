package edu.codegym.toam.repository;

import edu.codegym.toam.model.ContractStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractStatusRepository extends JpaRepository<ContractStatus,Long> {
}
