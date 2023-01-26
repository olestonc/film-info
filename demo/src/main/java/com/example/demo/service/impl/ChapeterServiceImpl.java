package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.persistence.dao.ChapeterDao;
import com.example.demo.persistence.entity.ChapeterEntity;
import com.example.demo.service.ChapeterService;

@Service
public class ChapeterServiceImpl implements ChapeterService {

    @Autowired
    ChapeterDao dao;

    @Override
    public List<ChapeterEntity> getAllChapeters() {
        return dao.findAll();
    }

    @Override
    public Optional<ChapeterEntity> getChapeterById(Long id) {
        return dao.findById(id);
    }

    @Override
    public ChapeterEntity save(ChapeterEntity Chapeter) {
        return dao.save(Chapeter);
    }

    @Override
    public void deleteChapeter(Long id) {
        dao.deleteById(id);
    }

}