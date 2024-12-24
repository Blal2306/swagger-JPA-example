package com.app.experiment.prototype_app.repo;

import com.app.experiment.prototype_app.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    public boolean existsByEmail(String email);
    public List<Student> findByEmail(String email);

    @Query("SELECT MAX(s.id) FROM Student s")
    public Long findMaxId();
}
