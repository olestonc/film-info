package com.example.demo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.entity.SeasonEntity;

public interface SeasonDao extends JpaRepository<SeasonEntity, Long> {
}
