package com.keshore.spring_boot_atomikos.repositories;

import org.springframework.data.repository.CrudRepository;

import com.keshore.spring_boot_atomikos.entities.City;

public interface CityRepository extends CrudRepository<City, Long> {

}
