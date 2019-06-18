package it.academy.attendance.services;

import it.academy.attendance.models.Class;
import it.academy.attendance.models.Lecture;
import it.academy.attendance.repositories.ClassRepository;
import it.academy.attendance.repositories.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LectureServiceImpl implements LectureService{
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private LectureRepository lectureRepository;

    @Override
    public List<Lecture> getAll() {
        return lectureRepository.findAll();
    }

    @Override
    public Lecture getById(Long id) {
        return lectureRepository.findById(id).get();
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            //TODO delete only if the current user is the teacher, or is Admin
            lectureRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            // TODO write to log object
            return false;
        }
    }

    @Override
    public Lecture save(Lecture item) {
        return lectureRepository.save(item);
    }

    @Override
    public Set<Lecture> getLecturesByClass(Long classId) {
        return classRepository.findById(classId).get().getLectures();
    }

    @Override
    public Lecture save(Lecture item, Long classId) {
        //TODO redo normally
        Class classObj = classRepository.findById(classId).get();
        save(item);
        classObj.getLectures().add(item);
        classRepository.save(classObj);
        return item;
    }
}
