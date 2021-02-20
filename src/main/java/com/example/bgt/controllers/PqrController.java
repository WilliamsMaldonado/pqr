package com.example.bgt.controllers;

import com.example.bgt.model.Pqr;
import com.example.bgt.model.PqrRequest;
import com.example.bgt.services.PqrService;
import com.example.bgt.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pqr")
public class PqrController {

    private static final Logger LOGGER = LogManager.getLogger(PqrController.class);

    @Autowired
    private PqrService service;

    @PostMapping({"", "/"})
    public ResponseEntity savePqr(@RequestBody PqrRequest pqrRequest) {
        try {
            LOGGER.info("SAVE PQR: {}", pqrRequest.getUsername());
            Pqr pqr = service.savePqr(pqrRequest);
            return new ResponseEntity<>(pqr, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.ERROR_RELATED_ID);
        } catch (Exception e) {
            LOGGER.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pqr> getPqr(@PathVariable String id) {
        LOGGER.info("GET PQR: {}", id);
        Pqr pqr = service.findById(id);
        return new ResponseEntity<>(pqr, (pqr != null ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<Pqr>> getPqrByUsername(@PathVariable String username) {
        LOGGER.info("GET PQR: {}", username);
        List<Pqr> pqrList = service.findByUsername(username);
        return new ResponseEntity<>(pqrList, HttpStatus.OK);
    }

    @GetMapping("/open")
    public ResponseEntity<List<Pqr>> getOpenPqr() {
        LOGGER.info("GET OPEN PQR");
        List<Pqr> pqrList = service.findByOpen();
        return new ResponseEntity<>(pqrList, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Pqr> update(@RequestBody Pqr pqr) {
        LOGGER.info("UPDATE PQR: {}", pqr.toString());
        Pqr pqr1 = service.update(pqr);
        return new ResponseEntity<>(pqr1, (pqr1 != null ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }

}
