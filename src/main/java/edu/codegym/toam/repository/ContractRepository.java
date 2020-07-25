package edu.codegym.toam.repository;

import edu.codegym.toam.model.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContractRepository extends JpaRepository<Contracts,Long> {
    Iterable<Contracts>findContractsByPropertiesId(Long propertyId);

    @Query(value = "select c from Contracts c where "
            + "c.properties.host.id = :propertyId"
    )
    Iterable<Contracts> findAllContracts(Long propertyId);
}
