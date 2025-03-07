package com.exampleapi.service;

import com.exampleapi.entity.Registration;
import com.exampleapi.exception.ResourceNotFound;
import com.exampleapi.payload.RegistrationDto;
import com.exampleapi.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = new ModelMapper();
    }

    public RegistrationDto saveRegistration(RegistrationDto registrationDto) {

        //convert entity to dto
        Registration registration = convertDtoToEntity(registrationDto);
        registration.setId(1L);
        Registration savedReg = registrationRepository.save(registration);

        //convert dto to entity
        RegistrationDto savedRegDto = convertEntityToDto(savedReg);
        return savedRegDto;


    }

    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }

//    public void updateRegistration(long id, Registration registration) {
//       // Optional<Registration> opReg = registrationRepository.findById(id);
//       // Registration reg = opReg.get();
//        reg.setName(registration.getName());
//        reg.setEmailId(registration.getEmailId());
//        reg.setMobile(registration.getMobile());
//        registrationRepository.save(reg);
//
//    }

    public List<Registration> getAllregistration() {
        List<Registration> registration = registrationRepository.findAll();
        return registration;
    }

    public Registration getRegistrationById(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(

                        () -> new ResourceNotFound("Record Not Found with id:"+id)
                );
        return registration;

//        Optional<Registration> opReg = registrationRepository.findById(id);
//        Registration reg= opReg.get();
//        return reg;
    }
    Registration convertDtoToEntity(
            RegistrationDto registrationDto
    ){
        Registration registration = modelMapper.map(registrationDto,Registration.class);
      return registration;
    }

    RegistrationDto convertEntityToDto(
            Registration registration)
    {
        RegistrationDto registrationDto = modelMapper.map(registration,RegistrationDto.class);
        return registrationDto;
    }

    public List<RegistrationDto> getAllregistration(
            int pageNo,
            int pageSize,
            String sortBy,
            String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page =PageRequest.of(pageNo,pageSize, sort);
        Page<Registration> records = registrationRepository.findAll(page);
        List<Registration> registration= records.getContent();
        List<RegistrationDto> registrationDtos = registration.stream().map(r -> convertEntityToDto(r)).collect(Collectors.toList());
        return registrationDtos;
    }



}
