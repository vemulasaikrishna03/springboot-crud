package com.project.crud.controller;

import com.project.crud.entity.Record;
import com.project.crud.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping
    public ResponseEntity<Record> createRecord(@RequestBody Record record) {
        Record createdRecord = recordService.saveRecord(record);
        return ResponseEntity.status(201).body(createdRecord);
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

        // Update the properties you want to change
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
