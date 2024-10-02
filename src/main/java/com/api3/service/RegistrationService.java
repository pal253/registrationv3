package com.api3.service;

import com.api3.entity.Registration;
import com.api3.payload.RegistrationDto;
import com.api3.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private RegistrationRepository rp;
    private ModelMapper mp;



    public RegistrationService (RegistrationRepository rp, ModelMapper mp) {
        this.rp = rp;

        this.mp = mp;
    }
    public List <RegistrationDto> getAllRegistrations(){
        List <Registration> hh = rp.findAll();
        List <RegistrationDto> dto = hh.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return dto;

    }
    public RegistrationDto createReg(RegistrationDto dto){
       Registration registration = mapToEntity(dto);
        Registration yu = rp.save(registration);
       RegistrationDto reg = mapToDto( yu);



        return reg;
    }
    public void deleteRegistration(long id){
        rp.deleteById(id);

    }
    public Registration updateReg(long id , Registration registration){
       Registration y = rp.findById(id).get();
       y.setId(id);
       y.setName(registration.getName());
       y.setEmail(registration.getEmail());
       y.setMobile(registration.getMobile());
       Registration savedEntity = rp.save(y) ;
       return savedEntity;
    }
   Registration  mapToEntity(RegistrationDto dto){
       Registration registration = mp.map(dto , Registration.class);
       // Registration registration = new Registration();
        //registration.setName(dto.getName());
        //registration.setEmail(dto.getEmail());
        //registration.setMobile(dto.getMobile());
        return registration;
    }
   RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto = mp.map(registration , RegistrationDto.class);
      //  RegistrationDto dto = new RegistrationDto();
       // dto.setName(registration.getName());
        //dto.setEmail(registration.getEmail());
        //dto.setMobile(registration.getMobile());
        return dto;

    }

    public RegistrationDto getRegById(long id) {
       Registration reg = rp.findById(id).orElseThrow(
               ()->new RuntimeException("record not found")
       );
     return   mapToDto(reg);
    }
}
