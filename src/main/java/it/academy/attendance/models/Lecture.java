package it.academy.attendance.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.invoke.LambdaConversionException;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classId;

    private LocalDateTime date;

    public Lecture(Long id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public Lecture(LocalDateTime date) {
        this.date = date;
    }
}
