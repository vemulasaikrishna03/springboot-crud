package com.project.crud.controller;



import com.project.crud.entity.Record;
import com.project.crud.entity.Zone;
import com.project.crud.service.RecordService;
import com.project.crud.service.ZoneService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
   @Cacheable(value = "recordCache", key = "#input")
    public ResponseEntity<?> getIP(@PathVariable String input) {


          int i = 0;
          String zoneName = "";
          int idx = 1;
  
          for (int j = input.length() - 1; j >= 0; j--) {
              char currentChar = input.charAt(j);
  
              if (currentChar == '.') {
                  i++;
              }
  
              if (i == 2) {
                  break;
              }
  
              zoneName = currentChar + zoneName;
              idx++;
          }
  
          String recordName = input.substring(0, input.length()-idx);
        
        System.out.println(recordName+zoneName);



        Zone zone = zoneService.getZoneByName(zoneName);

        if (zone == null) {
            return ResponseEntity.notFound().build();
        }

        Record record = recordService.getRecordByRecordNameAndZoneId(recordName, zone.getZoneId());

        if (record == null) {
            return ResponseEntity.notFound().build();
        }

        
        return ResponseEntity.ok(record.getIP());

        // String ipAddress=record.getIP();


        //  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://" + ipAddress);

        // return new RedirectView(builder.toUriString());


    }
}
