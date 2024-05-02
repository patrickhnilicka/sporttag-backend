package org.sporttag.backend.repositories;

import org.sporttag.backend.entities.Riege;
import org.sporttag.backend.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RiegeRepository extends ListCrudRepository<Riege, Long> {
    @Query("SELECT r FROM Riege r WHERE r.sporttagId = :sporttagId")
    List<Riege> getAllBySporttagId(@Param("sporttagId") long sporttagId);

    @Query("SELECT r FROM Riege r WHERE r.sporttagId = :sporttagId AND r.nummer = :nr")
    List<Riege> getBySporttagIdAndNr(@Param("nr") int nr, @Param("sporttagId") long sporttagId);
}
