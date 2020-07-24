package edu.codegym.toam.repository;

import edu.codegym.toam.model.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PropertiesRepository extends JpaRepository<Properties, Long>, JpaSpecificationExecutor<Properties> {

    Iterable<Properties> findPropertiesByHost_Id(Long hostId);
}
