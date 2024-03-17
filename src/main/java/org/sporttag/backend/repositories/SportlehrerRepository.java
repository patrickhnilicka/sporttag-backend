package org.sporttag.backend.repositories;

import org.sporttag.backend.entities.Sportlehrer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface SportlehrerRepository extends ListCrudRepository<Sportlehrer, Long> {

    @Query("SELECT s FROM Sportlehrer s WHERE s.kuerzel = :kuerzel")
    Sportlehrer findSportlehrerByKuerzel(
            @Param("kuerzel") String kuerzel);
}
