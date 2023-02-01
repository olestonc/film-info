package com.nttdata.bootcamp.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;

public interface ChapeterRepository extends PagingAndSortingRepository<ChapeterEntity, Long> {

}
