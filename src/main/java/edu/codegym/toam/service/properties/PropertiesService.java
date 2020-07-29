package edu.codegym.toam.service.properties;

import edu.codegym.toam.model.Addresses;
import edu.codegym.toam.model.Properties;
import edu.codegym.toam.model.PropertyStatus;
import edu.codegym.toam.repository.AddressRepository;
import edu.codegym.toam.repository.PropertiesRepository;
import edu.codegym.toam.repository.PropertiesStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PropertiesService implements IPropertiesService {
    @Autowired
    PropertiesRepository propertiesRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    PropertiesStatusRepository propertiesStatusRepository;

    @Override
    public Iterable<Properties> findAll() {
        return propertiesRepository.findAll();
    }

    @Override
    public Properties findById(Long id) {
        return propertiesRepository.findById(id).get();
    }

    @Override
    public Properties update(Properties properties) {
        return propertiesRepository.save(properties);
    }

    @Override
    public void removeById(Long id) {
        propertiesRepository.deleteById(id);
    }

    @Override
    public Properties create(Properties properties) {
        Addresses addresses = properties.getAddresses();
        addressRepository.save(addresses);
        PropertyStatus propertyStatus=propertiesStatusRepository.findById((long) 1).get();
        properties.setPropertyStatus(propertyStatus);
        return propertiesRepository.save(properties);
    }

    @Override
    public Iterable<Properties> findAllPropertiesByHostId(Long id) {
        return propertiesRepository.findPropertiesByHost_Id(id);
    }

    @Override
    public Iterable<Properties> findPropertiesByHostIdAndType(Long hostId, Long propertyTypeId) {
        return propertiesRepository.findPropertiesByHost_IdAndPropertiesTypes_Id(hostId, propertyTypeId);
    }

    @Override
    public Iterable<Properties> filterProperties(String key) {
        return propertiesRepository.filterProperties(key);
    }

    @Override
    public Iterable<Properties> findAllByPropertiesTypes(String name) {
        return propertiesRepository.findByPropertiesTypes(name);
    }

    @Override
    public Iterable<Properties> findPropertiesByType(Long propertyTypeId) {
        return propertiesRepository.findPropertiesByPropertiesTypes_Id(propertyTypeId);
    }

    @Override
    public Iterable<Properties> filterPropertiesAdvance(String address, int bathroom, int bedroom) {
        return null;
    }
}
