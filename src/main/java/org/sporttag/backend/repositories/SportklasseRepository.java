package org.sporttag.backend.repositories;

import org.sporttag.backend.entities.Sportklasse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SportklasseRepository extends ListCrudRepository<Sportklasse, Long> {
    @Query("SELECT s FROM Sportklasse s WHERE s.klassenname = :name AND s.sporttagId = :sporttagid")
    Sportklasse findSportklasseByName(
            @Param("name") String name, @Param("sporttagid") Long sporttagId);

    @Query("SELECT s FROM Sportklasse s WHERE s.sporttagId = :id")
    List<Sportklasse> findAllBySporttag(
            @Param("id") Long sporttagId);
}
