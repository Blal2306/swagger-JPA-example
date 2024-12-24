package com.app.experiment.prototype_app.repo;

import com.app.experiment.prototype_app.domain.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by jt on 6/13/17.
 */
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {

    List<UnitOfMeasure> findByDescription(String description);
}
