package com.opscore.service;

import com.opscore.dto.IncidentDTO;
import com.opscore.exception.IncidentNotFoundException;
import com.opscore.model.Incident;
import com.opscore.repository.IncidentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<Incident> findAllIncidents() {
        return incidentRepository.findAll();
    }

    public Incident createIncident(Incident incident){
        return incidentRepository.save(incident);

    }

    public Incident findIncidentById(Long incidentID){
        return incidentRepository.findById(incidentID)
                .orElseThrow(()-> new IncidentNotFoundException("Incident not found"));
    }

    public Incident updateIncident(Long incidentID, IncidentDTO incidentDTO){
        Incident incident = incidentRepository.findById(incidentID)
                .orElseThrow(()-> new IncidentNotFoundException("Incident not found"));

        incident.setIncidentTitle(incidentDTO.getIncidentTitle());
        incident.setIncidentDescription(incidentDTO.getIncidentDescription());
        incident.setIncidentPriority(incidentDTO.getIncidentPriority());

        return incidentRepository.save(incident);
    }

    public void deleteIncident(Long incidentID){
        if(!incidentRepository.existsById(incidentID)){
            throw new IncidentNotFoundException("Incident not found");
        }
        incidentRepository.deleteById(incidentID);
    }


}
