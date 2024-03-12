package org.sporttag.backend.repositories;

import org.sporttag.backend.dataclasses.Sportklasse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface SportklasseRepository extends ListCrudRepository<Sportklasse, Long> {
    @Query("SELECT s FROM Sportklasse s WHERE s.klassenname = :name")
    Sportklasse findSportklasseByName(
            @Param("name") String name);
}
