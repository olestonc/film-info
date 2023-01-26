package com.example.demo.persistence.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.entity.CategoryEntity;

public interface CategoryDao extends JpaRepository<CategoryEntity, Long> {
}
