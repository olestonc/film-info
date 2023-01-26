package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.persistence.dao.SeasonDao;
import com.example.demo.persistence.entity.SeasonEntity;
import com.example.demo.service.SeasonService;

@Service
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    SeasonDao dao;

    @Override
    public List<SeasonEntity> getAllSeasons() {
        return dao.findAll();
    }

    @Override
    public Optional<SeasonEntity> getSeasonById(Long id) {
        return dao.findById(id);
    }

    @Override
    public SeasonEntity save(SeasonEntity Season) {
        return dao.save(Season);
    }

    @Override
    public void deleteSeason(Long id) {
        dao.deleteById(id);
    }

}