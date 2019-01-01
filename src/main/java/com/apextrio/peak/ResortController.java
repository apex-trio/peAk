package com.apextrio.peak;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestController
public class ResortController {

    @Autowired
    public ResortRepository resortRepo;

    @GetMapping("/resorts/{id}")
    public Resort getResort(@PathVariable long id){
        return resortRepo.findById(id).get();
    }
}
