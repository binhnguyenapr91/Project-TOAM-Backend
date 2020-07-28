package edu.codegym.toam.service.district;

import edu.codegym.toam.model.Districts;
import edu.codegym.toam.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictService implements IDistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public Iterable<Districts> findAllDistrict() {
        return districtRepository.findAll();
    }
}
