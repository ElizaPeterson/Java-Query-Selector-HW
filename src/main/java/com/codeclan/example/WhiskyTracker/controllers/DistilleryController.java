package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistilleryController {
    private final DistilleryRepository distilleryRepository;

    public DistilleryController(DistilleryRepository distilleryRepository) {
        this.distilleryRepository = distilleryRepository;
    }

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> findDistilleriesByRegionQueryString(@RequestParam(name = "region", required = false) String region){
        if (region!=null){
            return new ResponseEntity<>(distilleryRepository.findByRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/{id}")
    public ResponseEntity getDistillery(@PathVariable Long id){
        return new ResponseEntity<>(distilleryRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/whiskies")
    public ResponseEntity<List<Distillery>> findWhiskiesFilterByAgeFromDistilleryQueryString(@RequestParam(name = "age", required = false) Integer year){
        return new ResponseEntity<>(distilleryRepository.findByWhiskiesYear(year));
    }

}
