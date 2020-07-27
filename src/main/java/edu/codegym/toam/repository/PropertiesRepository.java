package edu.codegym.toam.repository;

import edu.codegym.toam.model.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PropertiesRepository extends JpaRepository<Properties, Long>, JpaSpecificationExecutor<Properties> {

    Iterable<Properties> findPropertiesByHost_Id(Long hostId);

    Iterable<Properties> findPropertiesByHost_IdAndPropertiesTypes_Id(Long hostId, Long propertiesTypeId);


//    @Query(value = "select p from Properties p where "
//            + "p.host = :hostId and p.propertiesTypes = :propertiesTypeId"
//    )
//    Iterable<Properties> findPropertiesByHost_IdAndPropertiesTypes_Id(Long hostId, Long propertiesTypeId);


    @Query(value = "select p from Properties p where "
            + "p.addresses.districts.cities.name like CONCAT('%',:keyword,'%')"
            + "or p.addresses.districts.name like CONCAT('%',:keyword,'%')"
            + "or p.addresses.street like CONCAT('%',:keyword,'%')"
            + "or p.name like CONCAT('%',:keyword,'%')"
    )
    Iterable<Properties> filterProperties(String keyword);

    Iterable<Properties> findPropertiesByPropertiesTypes_Name(String name);
}
