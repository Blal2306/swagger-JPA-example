package com.app.experiment.prototype_app.domain;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;

    public Student(String n, String s) {
        this.name = n;
        this.email = s;
    }
}
