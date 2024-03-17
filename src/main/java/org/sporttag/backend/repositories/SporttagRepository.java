package org.sporttag.backend.repositories;

import org.sporttag.backend.entities.Sporttag;
import org.springframework.data.repository.ListCrudRepository;

public interface SporttagRepository extends ListCrudRepository<Sporttag, Long> { }
