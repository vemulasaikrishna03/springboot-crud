package com.project.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.crud.entity.Zone;

public interface ZoneRepository extends JpaRepository<Zone,Integer>{
    Zone findByZoneName(String zoneName);
}

