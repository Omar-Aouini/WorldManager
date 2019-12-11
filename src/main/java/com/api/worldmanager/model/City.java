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
@Table(name= "city")
public class City {
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "Name", nullable = false, length = 35)
    private String name;
    @Basic
    @Column(name = "District", nullable = false, length = 20)
    private String district;
    @Basic
    @Column(name = "Population", nullable = false)
    private int population;
}
