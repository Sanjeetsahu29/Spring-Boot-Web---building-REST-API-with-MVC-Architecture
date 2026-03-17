package org.sanjeet.springbootweb.repositories;

import org.sanjeet.springbootweb.entities.EmployeeEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntities, Long> {

}
