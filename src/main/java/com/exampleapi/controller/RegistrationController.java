package com.exampleapi.controller;

import com.exampleapi.entity.Registration;
import com.exampleapi.payload.RegistrationDto;
import com.exampleapi.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")



public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    //http://localhost:8080/api/v1/registration

    @PostMapping
    public ResponseEntity<RegistrationDto> Registration(
            @RequestBody RegistrationDto registrationDto
    ) {
        RegistrationDto r =registrationService.saveRegistration(registrationDto);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @DeleteMapping
    public String deleteRegistration(
            @RequestParam Long id
    ) {
        registrationService.deleteRegistration(id);
        return "deleted";
    }

    @PutMapping("{id}")
    public String updateRegistration(
            @PathVariable long id,
            @RequestBody Registration registration

    ) {
      //registrationService.updateRegistration(id, registration);
        return "Updated";
    }

    @GetMapping
    public List<RegistrationDto> getAllregistration(@RequestParam(defaultValue = "0",required = false)int pageNo,
                                                 @RequestParam(defaultValue = "5",required = false)int pageSize ,
                                                 @RequestParam(defaultValue = "id",required = false)String sortBy,
                                                 @RequestParam(defaultValue = "asc",required = false)String sortDir) {
        List<RegistrationDto> dtos = registrationService.getAllregistration(pageNo, pageSize,sortBy,sortDir);
        return dtos;
    }
    @GetMapping("/id/{id}")
    public Registration getRegistrationById(
            @PathVariable Long id
    )    {
        Registration registration= registrationService.getRegistrationById(id);
        return registration;
    }
}
