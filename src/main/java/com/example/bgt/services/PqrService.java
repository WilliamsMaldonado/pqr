package com.example.bgt.services;

import com.example.bgt.model.Pqr;
import com.example.bgt.model.PqrRequest;

import java.util.List;

public interface PqrService {
    Pqr savePqr(PqrRequest pqrRequest) throws Exception;
    Pqr findById(String id);
    List<Pqr> findByUsername(String username);
    List<Pqr> findByOpen();
    Pqr update(Pqr pqr);
}
