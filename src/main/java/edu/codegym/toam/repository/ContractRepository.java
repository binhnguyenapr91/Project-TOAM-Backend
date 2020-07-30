package edu.codegym.toam.repository;

import edu.codegym.toam.model.Contracts;
import edu.codegym.toam.model.LeaseTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContractRepository extends JpaRepository<Contracts,Long> {
    Iterable<Contracts>findContractsByPropertiesId(Long propertyId);
    Iterable<Contracts> findContractsByProperties_Host_Id(Long hostId);
    Iterable<Contracts> findContractsByRenter_Id(Long renterId);
    Iterable<Contracts> findContractsByRenter_IdAndProperties_Id(Long renterId,Long propertyId);

    Iterable<Contracts> findContractsByProperties_Host_IdAndCreateTimeContaining(Long hostId, String now);

//    @Query(value = "select c from Contracts c where "
//            + "c.renter.id = :renterId"
//    )
//    Iterable<Contracts> findAllContractsByRenterId(Long renterId);

}
