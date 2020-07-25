package edu.codegym.toam.repository;

import edu.codegym.toam.model.PropertiesTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyTypeRepository extends JpaRepository<PropertiesTypes, Long> {
}
