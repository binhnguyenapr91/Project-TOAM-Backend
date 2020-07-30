package edu.codegym.toam.repository;

import edu.codegym.toam.model.Contracts;
import edu.codegym.toam.model.LeaseTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ContractRepository extends JpaRepository<Contracts, Long> {
    Iterable<Contracts> findContractsByPropertiesId(Long propertyId);

    Iterable<Contracts> findContractsByProperties_Host_Id(Long hostId);

    Iterable<Contracts> findContractsByRenter_Id(Long renterId);

    Iterable<Contracts> findContractsByRenter_IdAndProperties_Id(Long renterId, Long propertyId);

    Iterable<Contracts> findContractsByProperties_Host_IdAndCreateTimeBetween(Long hostId, Date now, Date beforeOneMonth);

    @Query("SELECT c FROM Contracts c "
            + "WHERE c.properties.host.id =:hostId "
            + "AND MONTH (c.createTime) = :month "
            + "AND YEAR(c.createTime) = :year")
    Iterable<Contracts> findHistoryContractPerMonth(Integer year, Integer month, Long hostId);

    Iterable<Contracts> findAllByCreateTimeBetweenAndProperties_Host_Id(Date beginToTrack, Date now, Long hostId);

    Long countContractsByProperties_Host_Id(Long hostId);
}
