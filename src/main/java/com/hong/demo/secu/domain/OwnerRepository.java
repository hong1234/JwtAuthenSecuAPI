package com.hong.demo.secu.domain;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
 
public interface OwnerRepository extends CrudRepository<Owner, Long> {
	Optional<Owner> findByFirstname(String firstName);
}
