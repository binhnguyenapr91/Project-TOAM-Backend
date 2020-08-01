package edu.codegym.toam.service.propertyStatus;

import edu.codegym.toam.model.PropertyStatus;

public interface IPropertyStatusService{
    Iterable<PropertyStatus> getAll();
}
