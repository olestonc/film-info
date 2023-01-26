package com.example.demo.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.persistence.entity.CategoryEntity;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long> {

}
