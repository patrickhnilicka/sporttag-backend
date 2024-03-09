package org.sporttag.backend.repositories;

import org.sporttag.backend.dataclasses.Student;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface StudentRepository extends ListCrudRepository<Student, Long> {}

