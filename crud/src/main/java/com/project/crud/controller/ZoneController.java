package com.project.crud.controller;

import com.project.crud.entity.Zone;
import com.project.crud.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @GetMapping
    public ResponseEntity<List<Zone>> getAllZones() {
        List<Zone> zones = zoneService.getAllZones();
        return ResponseEntity.ok(zones);
    }

    @PostMapping
    public ResponseEntity<Zone> createZone(@RequestBody Zone zone) {
        Zone createdZone = zoneService.saveZone(zone);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdZone);
    }

    @PutMapping("/{zoneName}")
    public ResponseEntity<Zone> updateZone(@PathVariable String zoneName, @RequestBody Zone updatedZone) {
        Zone existingZone = zoneService.getZoneByName(zoneName);

        if (existingZone == null) {
            return ResponseEntity.notFound().build();
        }

        existingZone.setZoneName(updatedZone.getZoneName());

        Zone savedZone = zoneService.saveZone(existingZone);

        return ResponseEntity.ok(savedZone);
    }

    @DeleteMapping("/{zoneName}")
    public ResponseEntity<Void> deleteZoneByName(@PathVariable String zoneName) {
        Zone existingZone = zoneService.getZoneByName(zoneName);

        if (existingZone == null) {
            return ResponseEntity.notFound().build();
        }

        zoneService.deleteZone(existingZone.getZoneId());
        return ResponseEntity.noContent().build();
    }

}
