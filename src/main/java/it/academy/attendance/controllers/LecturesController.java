package it.academy.attendance.controllers;

import it.academy.attendance.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectures")
public class LecturesController {
    @Autowired
    private LectureService lectureService;

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('TEACHER') or hasAuthority('ADMIN')")
    public ResponseEntity deleteLecture(@RequestBody Long id) {
        try {
            return new ResponseEntity<>(lectureService.deleteById(id), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "");
        }
    }
}
