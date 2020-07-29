package edu.codegym.toam.service.propertyType;

import edu.codegym.toam.model.PropertiesTypes;

public interface IPropertyTypeService {
    Iterable<PropertiesTypes> findAll();
}
