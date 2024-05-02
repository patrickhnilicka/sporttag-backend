package org.sporttag.backend.repositories;

import org.sporttag.backend.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Repository
public interface StudentRepository extends ListCrudRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.sportklasse.sporttagId = :sporttagId")
    List<Student> getAllBySporttagId(@Param("sporttagId") long sporttagId);

    @Query("SELECT s FROM Student s WHERE s.riegeId = :riegeId")
    List<Student> getAllByRiegeId(@Param("riegeId") long riegeId);
}

