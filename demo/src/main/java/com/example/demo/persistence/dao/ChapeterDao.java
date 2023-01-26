package com.example.demo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.entity.ChapeterEntity;

public interface ChapeterDao extends JpaRepository<ChapeterEntity, Long> {
}
