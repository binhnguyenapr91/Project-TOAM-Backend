package edu.codegym.toam.repository;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.Properties;
import edu.codegym.toam.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertiesRepository extends JpaRepository<Properties, Long> {

    Iterable<Properties> findPropertiesByHost_Id(Long hostId);
}
