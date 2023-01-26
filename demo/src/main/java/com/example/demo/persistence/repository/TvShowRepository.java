package com.example.demo.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.persistence.entity.TvShowEntity;

public interface TvShowRepository extends PagingAndSortingRepository<TvShowEntity, Long> {

}
