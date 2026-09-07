package com.opscore.dto;

import lombok.Data;

@Data
public class IncidentDTO {
    private String incidentTitle;
    private String incidentDescription;
    private String incidentPriority;
}
