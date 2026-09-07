package com.opscore.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "incidents")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor

public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incidentID;

    private String incidentTitle;
    private String incidentDescription;
    private String incidentPriority;




}
