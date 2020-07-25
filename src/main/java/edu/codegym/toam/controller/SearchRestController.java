//package edu.codegym.toam.controller;
//
//import com.sipios.springsearch.anotation.SearchSpec;
//import edu.codegym.toam.model.Properties;
//import edu.codegym.toam.repository.PropertiesRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("api")
//public class SearchRestController {
//
//    @Autowired
//    PropertiesRepository propertiesRepository;
//
////    public void CarController(PropertiesRepository propertiesRepository) {
////        this.propertiesRepository = propertiesRepository;
////    }
//
//    @GetMapping("/province")
//    public ResponseEntity<List<Properties>> searchForCars(@SearchSpec Specification<Properties> specs) {
//        return new ResponseEntity<>(propertiesRepository.findAll(Specification.where(specs)), HttpStatus.OK);
//    }
//}
