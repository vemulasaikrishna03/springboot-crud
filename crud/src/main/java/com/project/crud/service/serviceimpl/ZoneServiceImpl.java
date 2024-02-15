package com.project.crud.service.serviceimpl;


import com.project.crud.entity.Zone;
import com.project.crud.repository.ZoneRepository;
import com.project.crud.service.ZoneService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZoneServiceImpl implements ZoneService  {

    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    @Transactional
    public Zone saveZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public Zone getZoneById(int zoneId) {
        return zoneRepository.findById(zoneId).orElse(null);
    }

    @Override
    @Transactional
    public void deleteZone(int zoneId) {
        zoneRepository.deleteById(zoneId);
    }
    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }
    
    @Override
    public Zone getZoneByName(String zoneName) {
        return zoneRepository.findByZoneName(zoneName);
    }
}
