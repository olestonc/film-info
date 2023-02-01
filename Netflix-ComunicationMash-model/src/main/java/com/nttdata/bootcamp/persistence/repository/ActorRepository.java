package com.nttdata.bootcamp.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nttdata.bootcamp.persistence.entity.ActorEntity;

public interface ActorRepository extends PagingAndSortingRepository<ActorEntity, Long> {

}
