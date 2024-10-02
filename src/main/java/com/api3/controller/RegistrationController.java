package com.api3.controller;

import com.api3.entity.Registration;
import com.api3.payload.RegistrationDto;
import com.api3.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private RegistrationService rs ;

    public RegistrationController(RegistrationService rs) {
        this.rs = rs;
    }
    @GetMapping
    public ResponseEntity<List <RegistrationDto>> getRegistrations(){
        List<RegistrationDto> w = rs.getAllRegistrations();
        return new ResponseEntity<>(w , HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<?> createAllRegistrations(
           @Valid @RequestBody RegistrationDto registrationDto , BindingResult result
    ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage() , HttpStatus.CREATED);
        }
       RegistrationDto dto = rs.createReg(registrationDto);
       return new ResponseEntity<>(dto , HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity <String> deleteReg(
            @RequestParam long id
    ){
        rs.deleteRegistration(id);
        return new ResponseEntity<>("Deleted" , HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity <Registration> updateRegistration(
            @RequestBody Registration registration ,
            @PathVariable long id
    ){
        Registration h = rs.updateReg(id ,registration);
        return new ResponseEntity<>(h , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity <RegistrationDto> getRegistrationById(@PathVariable long id){
       RegistrationDto dto = rs.getRegById(id);
       return new ResponseEntity<>(dto , HttpStatus.OK);

    }
}
