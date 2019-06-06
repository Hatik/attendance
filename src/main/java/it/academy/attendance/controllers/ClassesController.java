package it.academy.attendance.controllers;

import it.academy.attendance.Helper;
import it.academy.attendance.models.User;
import it.academy.attendance.models.Class;
import it.academy.attendance.services.ClassService;
import it.academy.attendance.services.UserService;
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
    @Autowired
    private UserService userService;
    @Autowired
    private Helper helper;

    @CrossOrigin
    @PostAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<Class> getAllClasses(){
        return classService.getAll();
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/my")
    public List<Class> getAllClassesByTeacher(){
        User user = helper.getCurrentUser();
        return classService.getAllClassesByTeacher(user);
    }

    //Only teacher
    //Assigns to the teacher created class
    @PostAuthorize("hasAuthority('TEACHER')")
    @PostMapping
    public ResponseEntity createClass(@RequestBody Class c){
        try {
            User currentUser = helper.getCurrentUser();
            c.setTeacher(currentUser);
            return new ResponseEntity<>(classService.save(c), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "");
        }
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity deleteClass(@RequestBody Long id) {
        try {
            return new ResponseEntity<>(classService.deleteById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "");
        }
    }


}
