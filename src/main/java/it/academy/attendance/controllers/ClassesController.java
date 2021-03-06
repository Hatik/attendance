package it.academy.attendance.controllers;

import it.academy.attendance.dtos.UserEnrollDto;
import it.academy.attendance.helpers.Helper;
import it.academy.attendance.models.Lecture;
import it.academy.attendance.models.User;
import it.academy.attendance.models.Class;
import it.academy.attendance.services.ClassService;
import it.academy.attendance.services.LectureService;
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
    private LectureService lectureService;
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
    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping
    public ResponseEntity createClass(@RequestBody Class c){
        try {
            //TODO current user to service
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


    @CrossOrigin
    @PreAuthorize("hasAuthority('TEACHER') or hasAuthority('STUDENT') or hasAuthority('ADMIN')")
    @GetMapping("/{id}/lectures")
    public ResponseEntity getLecturesByClass(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(lectureService.getLecturesByClass(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e + "", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('TEACHER') or hasAuthority('ADMIN')")
    @PostMapping("/{id}/lectures")
    public ResponseEntity addLectureToClass(@PathVariable("id") Long id, @RequestBody Lecture lecture){
        try {
            return new ResponseEntity<>(lectureService.save(lecture, id), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "");
        }
    }

    @PreAuthorize("hasAuthority('TEACHER') or hasAuthority('ADMIN') or hasAuthority('STUDENT')")
    @PostMapping("/{id}/enrollById")
    public ResponseEntity enrollStudentToClassById(@PathVariable("id") Long id, @RequestBody UserEnrollDto userDto){
        try {
            return new ResponseEntity<>(classService.enrollStudent(id, userDto.getUserId()), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "");
        }
    }

    @PreAuthorize("hasAuthority('TEACHER') or hasAuthority('ADMIN') or hasAuthority('STUDENT')")
    @PostMapping("/{id}/unrollById")
    public ResponseEntity unrollStudentFromClassById(@PathVariable("id") Long id, @RequestBody UserEnrollDto userDto){
        try {
            return new ResponseEntity<>(classService.unrollStudent(id, userDto.getUserId()), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "");
        }
    }

}
