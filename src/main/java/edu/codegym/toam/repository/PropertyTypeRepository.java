package edu.codegym.toam.repository;

import edu.codegym.toam.model.PropertiesTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertiesTypes, Long> {
    PropertiesTypes findByName(String name);
}
