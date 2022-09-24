package com.pe.studynow.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.studynow.model.entity.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{

}
