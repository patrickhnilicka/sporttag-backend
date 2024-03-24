package org.sporttag.backend.repositories;

import org.sporttag.backend.entities.Riege;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface RiegeRepository extends ListCrudRepository<Riege, Long> {
}
