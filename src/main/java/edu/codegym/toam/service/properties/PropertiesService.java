package edu.codegym.toam.service.properties;

import edu.codegym.toam.model.Properties;
import edu.codegym.toam.repository.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PropertiesService implements IPropertiesService {
    @Autowired
    PropertiesRepository propertiesRepository;

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
        return propertiesRepository.save(properties);
    }

    @Override
    public Iterable<Properties> findAllPropertiesById(Long id) {
        return propertiesRepository.findPropertiesByHost_Id(id);
    }
    //    @Override
//    public Iterable<Properties> findByLocation(String locationName) {
//        return propertiesRepository.findPropertiesByAddresses_Districts(locationName);
//    }
}
