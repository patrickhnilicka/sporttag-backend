package org.sporttag.backend.repositories;

import org.sporttag.backend.entities.Riege;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface RiegeRepository extends ListCrudRepository<Riege, Long> {
    @Query("SELECT r FROM Riege r WHERE r.sportklassen.id = :sportklassenId AND r.isdefault = true")
    Riege getDefaultRiegeFromSportklasseId(@Param("sportklassenId") long sportklassenId);

    @Query("SELECT r FROM Riege r INNER JOIN r.sportklassen s WHERE s.klassenname = :sportklasse AND r.isdefault = true")
    Riege getDefaultRiegeFromSportklasse(@Param("sportklasse") String sportklasse);
}
