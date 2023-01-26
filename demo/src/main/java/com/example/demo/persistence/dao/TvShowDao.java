package com.example.demo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.entity.TvShowEntity;

public interface TvShowDao extends JpaRepository<TvShowEntity, Long> {
}
