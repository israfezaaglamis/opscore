package com.opscore.controller;

import com.opscore.dto.IncidentDTO;
import com.opscore.model.Incident;
import com.opscore.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")

public class IncidentController {

   private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public ResponseEntity<List<Incident>> getIncidents() {
        return ResponseEntity.ok(incidentService.findAllIncidents());

    }
    @GetMapping("/{incidentID}")
    public ResponseEntity<Incident> getIncidentByID(@PathVariable Long incidentID) {
        return ResponseEntity.ok(
                incidentService.findIncidentById(incidentID)
        );
    }

    @PostMapping
    public ResponseEntity<Incident>createIncident(@Valid @RequestBody Incident incident){

         Incident createIncident = incidentService.createIncident(incident);
         return new ResponseEntity<>(createIncident, HttpStatus.CREATED);
    }

    @PutMapping("/{incidentID}")
    public ResponseEntity<Incident>updateIncident(@PathVariable Long incidentID,@RequestBody IncidentDTO incidentDTO){

        Incident updateIncident = incidentService.updateIncident(incidentID,incidentDTO);
        return ResponseEntity.ok(updateIncident);
    }



    @DeleteMapping("/{incidentID}")
    public ResponseEntity<Void>deleteIncident(@PathVariable Long incidentID){
       incidentService.deleteIncident(incidentID);

       return ResponseEntity.noContent().build();
    }
}

