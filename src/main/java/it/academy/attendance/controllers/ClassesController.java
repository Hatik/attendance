package it.academy.attendance.controllers;

import it.academy.attendance.models.User;
import it.academy.attendance.models.Class;
import it.academy.attendance.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private ClassService classService;

    @CrossOrigin
    @PostAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<Class> getAllClasses(){
        return classService.getAll();
    }

    @PostMapping
    public ResponseEntity createClass(@RequestBody Class c){
        try {
            return new ResponseEntity<>(classService.save(c), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "");
        }
    }


}
