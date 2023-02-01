package com.nttdata.bootcamp.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nttdata.bootcamp.persistence.entity.CategoryEntity;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long> {

}
