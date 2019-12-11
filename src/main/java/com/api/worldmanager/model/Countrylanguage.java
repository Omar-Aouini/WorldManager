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
@Table(name= "countrylanguage")
@IdClass(CountrylanguagePK.class)
public class Countrylanguage {
    @Id
    @Column(name = "CountryCode", nullable = false, length = 3, insertable = false, updatable = false)
    private String countryCode;
    @Id
    @Column(name = "Language", nullable = false, length = 30)
    private String language;
    @Basic
    @Column(name = "IsOfficial", nullable = false)
    private String isOfficial;
    @Basic
    @Column(name = "Percentage", nullable = false, precision = 1)
    private double percentage;
    @ManyToOne
    @JoinColumn(name = "CountryCode", referencedColumnName = "Code", nullable = false, insertable = false, updatable = false)
    private Country countryByCountryCode;
}
