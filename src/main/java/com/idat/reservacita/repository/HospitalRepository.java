package com.idat.reservacita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idat.reservacita.model.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer>{}