package edu.codegym.toam.service.propertyStatus;

import edu.codegym.toam.model.PropertyStatus;
import edu.codegym.toam.repository.PropertiesRepository;
import edu.codegym.toam.repository.PropertiesStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyStatusService implements IPropertyStatusService{
    @Autowired
    private PropertiesStatusRepository propertiesRepository;
    @Override
    public Iterable<PropertyStatus> getAll() {
        return propertiesRepository.findAll();
    }
}
