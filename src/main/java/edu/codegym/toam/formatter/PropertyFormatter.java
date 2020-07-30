package edu.codegym.toam.formatter;

import edu.codegym.toam.model.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

import edu.codegym.toam.service.properties.IPropertiesService;
import edu.codegym.toam.service.properties.PropertiesService;

@Component
public class PropertyFormatter implements Formatter<Properties> {
    @Autowired
    IPropertiesService propertiesService;

    @Autowired
    public PropertyFormatter(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @Override
    public Properties parse(String text, Locale locale) throws ParseException {
        return this.propertiesService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Properties object, Locale locale) {
        return null;
    }
}
