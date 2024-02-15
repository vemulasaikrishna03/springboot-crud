package com.project.crud.service;

import java.util.List;

import com.project.crud.entity.Zone;

public interface ZoneService {
    Zone saveZone(Zone zone);
    Zone getZoneById(int zoneId);
    void deleteZone(int zoneId);
    List<Zone> getAllZones();
    Zone getZoneByName(String zoneName);
}
