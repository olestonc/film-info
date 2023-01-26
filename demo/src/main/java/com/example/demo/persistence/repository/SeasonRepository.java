package com.example.demo.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.persistence.entity.SeasonEntity;

public interface SeasonRepository extends PagingAndSortingRepository<SeasonEntity, Long> {

}
