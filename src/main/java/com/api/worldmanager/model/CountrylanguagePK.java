package com.api.worldmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class CountrylanguagePK implements Serializable {
    @Column(name = "CountryCode", nullable = false, length = 3)
    @Id
    private String countryCode;
    @Column(name = "Language", nullable = false, length = 30)
    @Id
    private String language;
}
