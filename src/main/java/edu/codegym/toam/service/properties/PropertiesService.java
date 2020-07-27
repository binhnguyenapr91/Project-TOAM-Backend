package edu.codegym.toam.service.properties;

import edu.codegym.toam.model.Addresses;
import edu.codegym.toam.model.Properties;
import edu.codegym.toam.repository.AddressRepository;
import edu.codegym.toam.repository.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PropertiesService implements IPropertiesService {
    @Autowired
    PropertiesRepository propertiesRepository;
    @Autowired
    AddressRepository addressRepository;

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

}
