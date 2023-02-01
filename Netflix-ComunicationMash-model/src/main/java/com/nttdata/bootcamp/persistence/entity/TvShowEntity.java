package com.nttdata.bootcamp.persistence.entity;

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
@Table(name = "tv_show") // Por defecto nombre de la clase
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TvShowEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tvShowId;

    private String tvShowName;

    private String tvShowShortDescription;

    @Column(length = 65555)
    private String tvShowLongDescription;// Algún tipo de dato más apropiado?

    private int tvShowYear;

    private int tvShowRecommendedAge;

    private boolean tvShowAdvertising;// El programa está propocionado

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity tvShowCategory;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<SeasonEntity> tvShowSeasons;

}
