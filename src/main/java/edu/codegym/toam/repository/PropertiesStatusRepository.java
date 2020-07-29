package edu.codegym.toam.repository;

import edu.codegym.toam.model.PropertyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertiesStatusRepository extends JpaRepository<PropertyStatus,Long> {
}
