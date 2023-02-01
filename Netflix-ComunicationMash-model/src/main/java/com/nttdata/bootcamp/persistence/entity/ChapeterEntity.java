package com.nttdata.bootcamp.persistence.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chapeter")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChapeterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapeterId;

    private int chapeterNumber;

    private String name;

    private int chapeterDuration;// mins

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    private Set<ActorEntity> chapeterActors;

    @ManyToOne(fetch = FetchType.LAZY)
    private SeasonEntity chapeterSeason;

}
