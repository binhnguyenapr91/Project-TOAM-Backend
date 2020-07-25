package edu.codegym.toam.service.propertyType;

import edu.codegym.toam.model.PropertiesTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.codegym.toam.repository.PropertyTypeRepository;

@Service
public class PropertyService implements IPropertyTypeService {
    @Autowired
    private PropertyTypeRepository propertyRepository;

    @Override
    public Iterable<PropertiesTypes> findAll() {
        return this.propertyRepository.findAll();
    }
}
