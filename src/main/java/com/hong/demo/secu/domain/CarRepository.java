package com.hong.demo.secu.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;
// import org.springframework.stereotype.Repository; 
 
// @Repository 
// @RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long> {
	// Fetch cars by brand
	List<Car> findByBrand(@Param("brand") String brand);
	// Fetch cars by color
	List<Car> findByColor(@Param("color") String color);
}
