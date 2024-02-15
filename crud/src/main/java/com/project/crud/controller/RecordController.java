package com.project.crud.controller;

import com.project.crud.entity.Record;
import com.project.crud.entity.Zone;
import com.project.crud.service.RecordService;
import com.project.crud.service.ZoneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/records")
public class RecordController {

    @Autowired
    private RecordService recordService;
    private ZoneService zoneService;


    @PostMapping("/create")
    public ResponseEntity<Record> createRecord(@RequestBody Map<String, String> requestMap) {
        String recordName = requestMap.get("recordName");
        String zoneName = requestMap.get("zoneName");
        String ip = requestMap.get("IP");

        Zone zone = zoneService.getZoneByName(zoneName);
        if (zone == null) {
            return ResponseEntity.notFound().build();
        }

        Record record = new Record();
        record.setRecordName(recordName);
        record.setZone(zone);
        record.setIP(ip);
        
        Record createdRecord = recordService.saveRecord(record);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }

    @PutMapping("/{recordName}/{zoneName}")
    public ResponseEntity<Record> updateRecord(
            @PathVariable String recordName,
            @PathVariable String zoneName,
            @RequestBody Record updatedRecord) {

        Record existingRecord = recordService.getRecordByRecordNameAndZoneName(recordName, zoneName);

        if (existingRecord == null) {
            return ResponseEntity.notFound().build();
        }


        existingRecord.setRecordName(updatedRecord.getRecordName());
        existingRecord.setIP(updatedRecord.getIP());

        Record savedRecord = recordService.saveRecord(existingRecord);

        return ResponseEntity.ok(savedRecord);
    }

    @DeleteMapping("/{recordName}/{zoneName}")
    public ResponseEntity<Void> deleteRecord(
            @PathVariable String recordName,
            @PathVariable String zoneName) {

        Record existingRecord = recordService.getRecordByRecordNameAndZoneName(recordName, zoneName);

        if (existingRecord == null) {
            return ResponseEntity.notFound().build();
        }

        recordService.deleteRecord(existingRecord.getRecordId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAllByZone/{zoneName}")
    public ResponseEntity<List<Record>> getAllRecordsByZoneName(@PathVariable String zoneName) {
        List<Record> records = recordService.getAllRecordsByZoneName(zoneName);
        return ResponseEntity.ok(records);
    }

  
}
