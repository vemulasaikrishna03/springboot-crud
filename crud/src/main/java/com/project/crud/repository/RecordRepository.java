package com.project.crud.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.crud.entity.Record;

public interface RecordRepository extends JpaRepository<Record,Integer>{
    Record findByRecordNameAndZoneZoneId(String recordName, int zoneId);
    List<Record> findByZoneZoneName(String zoneName);
    Record findByRecordNameAndZoneZoneName(String recordName, String zoneName);
}
