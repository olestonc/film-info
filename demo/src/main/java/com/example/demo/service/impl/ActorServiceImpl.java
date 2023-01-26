package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.dao.ActorDao;
import com.example.demo.persistence.entity.ActorEntity;
import com.example.demo.service.ActorService;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorDao dao;

    @Override
    public List<ActorEntity> getAllActors() {
        return dao.findAll();
    }

    @Override
    public Optional<ActorEntity> getActorById(Long id) {
        return dao.findById(id);
    }

    @Override
    public ActorEntity save(ActorEntity Actor) {
        return dao.save(Actor);
    }

    @Override
    public void deleteActor(Long id) {
        dao.deleteById(id);
    }

}