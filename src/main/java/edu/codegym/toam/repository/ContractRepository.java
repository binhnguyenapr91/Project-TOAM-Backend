package edu.codegym.toam.repository;

import edu.codegym.toam.model.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ContractRepository extends JpaRepository<Contracts, Long> {
    Iterable<Contracts> findContractsByPropertiesId(Long propertyId);

    Iterable<Contracts> findContractsByProperties_Host_Id(Long hostId);

    Iterable<Contracts> findContractsByRenter_Id(Long renterId);

    Iterable<Contracts> findContractsByRenter_IdAndProperties_Id(Long renterId, Long propertyId);

//        @Query("select c from Contracts c where " +
//                "c.properties.host.id =:hostId and( c.createTime <= :now and c.createTime>:beforeOneMonth)")
//    Iterable<Contracts> findContractsByProperties_Host_IdAndTime(Long hostId, Date now, Date beforeOneMonth);

    Iterable<Contracts> findContractsByProperties_Host_IdAndCreateTimeBetween(Long hostId, Date now, Date beforeOneMonth);

//    @Query("select c from Contracts c where c.createTime <= :creationDateTime")
//    Iterable<Contracts> findContractsByProperties_Host_IdAndCreateTimeBetween(Long hostId, Date creationDateTime);

    Iterable<Contracts> findAllByCreateTimeBetweenAndProperties_Host_Id(Date beginToTrack,Date now,Long hostId);
    Iterable<Contracts> findAllByCreateTimeBetween(Date beginToTrack,Date now);

}
