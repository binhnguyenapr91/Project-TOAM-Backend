package edu.codegym.toam.repository;

import edu.codegym.toam.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusContractRepository extends JpaRepository<Status,Long> {
}
