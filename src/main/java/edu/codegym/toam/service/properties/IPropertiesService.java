package edu.codegym.toam.service.properties;

import edu.codegym.toam.model.Properties;

public interface IPropertiesService {
    Iterable<Properties> findAll();

    Properties findById(Long id);

    Properties update(Properties properties);

    void removeById(Long id);

    Properties create(Properties properties);

    Iterable<Properties> findAllPropertiesByHostId(Long id);

    Iterable<Properties> findPropertiesByHostIdAndType(Long hostId, Long propertyTypeId);

}
