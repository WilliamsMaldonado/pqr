package com.example.bgt.repository;

import com.example.bgt.model.Pqr;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PqrRepository extends MongoRepository<Pqr, String> {
    List<Pqr> findByUsername(String username);
    List<Pqr> findByReply(String reply);
}
