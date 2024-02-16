package com.project.crud.service;


import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.project.crud.entity.Record;

public interface RecordService {
    Record saveRecord(Record record);
    Record getRecordById(int recordId);
    void deleteRecord(int recordId);
    List<Record> getAllRecordsByZoneName(String zoneName);

    @Cacheable(value = "recordCache", key = "#recordName + '_' + #zoneId")
    Record getRecordByRecordNameAndZoneId(String recordName, int zoneId);
    Record getRecordByRecordNameAndZoneName(String recordName, String zoneName);
}


