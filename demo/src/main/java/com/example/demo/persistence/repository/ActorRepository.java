package com.example.demo.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.persistence.entity.ActorEntity;

public interface ActorRepository extends PagingAndSortingRepository<ActorEntity, Long> {

}
