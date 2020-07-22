package edu.codegym.toam.repository;

import edu.codegym.toam.model.Properties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertiesRepository extends JpaRepository<Properties, Long> {
    Iterable<Properties> findPropertiesByAddresses_Districts(String locationName);
}
