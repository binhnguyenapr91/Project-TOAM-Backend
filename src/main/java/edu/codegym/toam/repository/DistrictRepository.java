package edu.codegym.toam.repository;

import edu.codegym.toam.model.Districts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<Districts, Long> {
    Iterable<Districts> getAllByCities_Id(Long cityId);
}
