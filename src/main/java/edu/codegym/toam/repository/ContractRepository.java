package edu.codegym.toam.repository;

import edu.codegym.toam.model.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contracts,Long> {
    Iterable<Contracts>findContractsByPropertiesId(Long propertyId);

    Iterable<Contracts> findContractsByProperties_Host_Id(Long hostId);
    Iterable<Contracts> findContractsByRenter_Id(Long renterId);
    Iterable<Contracts> findContractsByRenter_IdAndProperties_Id(Long renterId,Long propertyId);

//    @Query(value = "select c from Contracts c where "
//            + "c.renter.id = :renterId"
//    )
//    Iterable<Contracts> findAllContractsByRenterId(Long renterId);

}
