package com.example.demo.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.persistence.entity.ChapeterEntity;

public interface ChapeterRepository extends PagingAndSortingRepository<ChapeterEntity, Long> {

}
