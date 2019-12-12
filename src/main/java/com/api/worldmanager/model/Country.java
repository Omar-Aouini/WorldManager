package com.api.worldmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "country")
public class Country{

    @Id
    @Column(name = "Code", nullable = false, length = 3)
    private String code;
    @Basic
    @Column(name = "Name", nullable = false, length = 52)
    private String name;
    @Basic
    @Column(name = "Continent", nullable = false)
    private String continent;
    @Basic
    @Column(name = "Region", nullable = false, length = 26)
    private String region;
    @Basic
    @Column(name = "SurfaceArea", nullable = false, precision = 2)
    private Double surfaceArea;
    @Basic
    @Column(name = "IndepYear")
    private Short indepYear;
    @Basic
    @Column(name = "Population", nullable = false)
    private int population;
    @Basic
    @Column(name = "LifeExpectancy", precision = 1)
    private Double lifeExpectancy;
    @Basic
    @Column(name = "GNP", precision = 2)
    private Double gnp;
    @Basic
    @Column(name = "GNPOld", precision = 2)
    private Double gnpOld;
    @Basic
    @Column(name = "LocalName", length = 45)
    private String localName;
    @Basic
    @Column(name = "GovernmentForm", nullable = false, length = 45)
    private String governmentForm;
    @Basic
    @Column(name = "HeadOfState", length = 60)
    private String headOfState;
    @Basic
    @Column(name = "Capital")
    private Integer capital;
    @Basic
    @Column(name = "Code2", nullable = false, length = 2)
    private String code2;

    }

