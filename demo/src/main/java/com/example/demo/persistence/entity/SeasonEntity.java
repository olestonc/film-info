package com.example.demo.persistence.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "season") // Por defecto nombre de la clase
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SeasonEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;
    
    private String name;

    @Column(length = 65555)
    private String longDescription;// Algún tipo de dato más apropiado?

    @OneToMany(mappedBy = "season", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<ChapeterEntity> chapeters;

    @ManyToOne(fetch = FetchType.LAZY)
    private TvShowEntity tvShow;

}
