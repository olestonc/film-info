package com.example.demo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.entity.ActorEntity;

public interface ActorDao extends JpaRepository<ActorEntity, Long> {
}
