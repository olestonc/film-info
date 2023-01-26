package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.dao.TvShowDao;
import com.example.demo.persistence.entity.TvShowEntity;
import com.example.demo.service.TvShowService;

@Service
public class TvShowServiceImpl implements TvShowService {

    @Autowired
    TvShowDao dao;

    @Override
    public List<TvShowEntity> getAllTvShows() {
        return dao.findAll();
    }

    @Override
    public Optional<TvShowEntity> getTvShowById(Long id) {
        return dao.findById(id);
    }

    @Override
    public TvShowEntity save(TvShowEntity TvShow) {
        return dao.save(TvShow);
    }

    @Override
    public void deleteTvShow(Long id) {
        dao.deleteById(id);
    }

}