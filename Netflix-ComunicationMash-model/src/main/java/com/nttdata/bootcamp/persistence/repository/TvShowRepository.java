package com.nttdata.bootcamp.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nttdata.bootcamp.persistence.entity.TvShowEntity;

public interface TvShowRepository extends PagingAndSortingRepository<TvShowEntity, Long> {

}
