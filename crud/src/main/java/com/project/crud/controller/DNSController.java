package com.project.crud.controller;



import com.project.crud.entity.Record;
import com.project.crud.entity.Zone;
import com.project.crud.service.RecordService;
import com.project.crud.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class DNSController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private ZoneService zoneService;

    @GetMapping("/{input}")
    public ResponseEntity<?> getIP(@PathVariable String input) {

            int lastDotIndex = input.lastIndexOf(".");
        
        if (lastDotIndex == -1 || lastDotIndex == 0 || lastDotIndex == input.length() - 1) {
            return ResponseEntity.badRequest().body("Invalid input format. Use recordName.domainName");
        }

        int secondLastDotIndex = input.lastIndexOf(".", lastDotIndex - 1);

        if (secondLastDotIndex == -1) {
            return ResponseEntity.badRequest().body("Invalid input format. Use recordName.domainName");
        }

        String recordName = input.substring(secondLastDotIndex + 1, lastDotIndex);
        String zoneName = input.substring(lastDotIndex + 1);



        Zone zone = zoneService.getZoneByName(zoneName);

        if (zone == null) {
            return ResponseEntity.notFound().build();
        }

        Record record = recordService.getRecordByRecordNameAndZoneId(recordName, zone.getZoneId());

        if (record == null) {
            return ResponseEntity.notFound().build();
        }

  
        return ResponseEntity.ok(record.getIP());
    }
}
