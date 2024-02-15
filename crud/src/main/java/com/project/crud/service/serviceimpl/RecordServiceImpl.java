package com.project.crud.service.serviceimpl;



import com.project.crud.entity.Record;
import com.project.crud.repository.RecordRepository;
import com.project.crud.service.RecordService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    @Transactional
    public Record saveRecord(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public Record getRecordById(int recordId) {
        return recordRepository.findById(recordId).orElse(null);
    }

    @Override
    @Transactional
    public void deleteRecord(int recordId) {
        recordRepository.deleteById(recordId);
    }

    @Override
    public List<Record> getAllRecordsByZoneName(String zoneName) {
        return recordRepository.findByZoneZoneName(zoneName);
    }

    @Override
    public Record getRecordByRecordNameAndZoneId(String recordName, int zoneId) {
        return recordRepository.findByRecordNameAndZoneZoneId(recordName, zoneId);
    }
    @Override
    public Record getRecordByRecordNameAndZoneName(String recordName, String zoneName) {
        return recordRepository.findByRecordNameAndZoneZoneName(recordName, zoneName);
    }

}

