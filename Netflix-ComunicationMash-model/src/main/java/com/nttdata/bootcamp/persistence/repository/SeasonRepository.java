package com.nttdata.bootcamp.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nttdata.bootcamp.persistence.entity.SeasonEntity;

public interface SeasonRepository extends PagingAndSortingRepository<SeasonEntity, Long> {

}
