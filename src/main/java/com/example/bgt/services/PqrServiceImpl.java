package com.example.bgt.services;

import com.example.bgt.model.Pqr;
import com.example.bgt.model.PqrRequest;
import com.example.bgt.repository.PqrRepository;
import com.example.bgt.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class PqrServiceImpl implements PqrService{

    private static final Logger LOGGER = LogManager.getLogger(PqrService.class);

    @Autowired
    private PqrRepository pqrRepository;

    @Override
    public Pqr savePqr(PqrRequest pqrRequest) throws Exception {
        Pqr pqrRelated = null;
        if (pqrRequest.getType() == Constants.RECLAMO) {
            Optional<Pqr> pqrR = pqrRepository.findById(pqrRequest.getRelatedId());
            if (pqrR.isPresent()) {
                LocalDate today = LocalDate.now();
                LocalDate createAt = pqrR.get().getCreateAt().toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate();
                long days = DAYS.between(createAt, today);
                if (days > Constants.DAYS_RECLAMO || (pqrR.get().getReply() != null && !pqrR.get().getReply().isEmpty())) {
                    pqrRelated = pqrR.get();
                }
            }
        }
        return pqrRepository.save(Pqr.getPqr(pqrRequest, pqrRelated));
    }

    @Override
    public Pqr findById(String id) {
        Optional<Pqr> pqr = pqrRepository.findById(id);
        return pqr.orElse(null);
    }

    @Override
    public List<Pqr> findByUsername(String username) {
        return pqrRepository.findByUsername(username);
    }

    @Override
    public List<Pqr> findByOpen() {
        return pqrRepository.findByReply(null);
    }

    @Override
    public Pqr update(Pqr pqr) {
        Optional<Pqr> pqrGet = pqrRepository.findById(pqr.getId());
        if (pqrGet.isPresent()) {
            Pqr pqrUpdate = pqrGet.get();
            pqrUpdate.setReply(pqr.getReply());
            pqrUpdate.setUpdateAt(new Date());
            pqrUpdate = pqrRepository.save(pqrUpdate);
            return pqrUpdate;
        }
        return null;
    }

}
