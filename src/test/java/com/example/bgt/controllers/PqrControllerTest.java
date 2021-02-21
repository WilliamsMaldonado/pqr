package com.example.bgt.controllers;

import com.example.bgt.model.Pqr;
import com.example.bgt.model.PqrRequest;
import com.example.bgt.services.PqrService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PqrControllerTest {

    @Mock private PqrService service;

    @InjectMocks private PqrController controller;

    @Test
    public void savePqrTestOK() throws Exception {
        Pqr pqr = new Pqr();
        pqr.setId("123456");
        when(service.savePqr(Mockito.any())).thenReturn(pqr);
        ResponseEntity response = controller.savePqr(new PqrRequest());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pqr.getId(), ((Pqr) response.getBody()).getId());
    }

    @Test
    public void savePqrException() throws Exception {
        when(service.savePqr(Mockito.any())).thenThrow(new Exception("ERROR"));
        ResponseEntity response = controller.savePqr(new PqrRequest());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void savePqrIllegalArgumentException() throws Exception {
        when(service.savePqr(Mockito.any())).thenThrow(new IllegalArgumentException("ILLEGAL ARG"));
        ResponseEntity response = controller.savePqr(new PqrRequest());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getPqrTestOK() {
        Pqr pqr = new Pqr();
        pqr.setId("123456");
        when(service.findById(Mockito.any())).thenReturn(pqr);
        ResponseEntity response = controller.getPqr("id");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pqr.getId(), ((Pqr) response.getBody()).getId());
    }

    @Test
    public void getPqrTestNotFound() {
        when(service.findById(Mockito.any())).thenReturn(null);
        ResponseEntity response = controller.getPqr("id");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getPqrByUsernameTestOK() {
        when(service.findByUsername(Mockito.any())).thenReturn(new ArrayList<>());
        ResponseEntity response = controller.getPqrByUsername("username");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getOpenPqrTestOK() {
        when(service.findByOpen()).thenReturn(new ArrayList<>());
        ResponseEntity response = controller.getOpenPqr();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateTestOK() {
        Pqr pqr = new Pqr();
        pqr.setId("123456");
        when(service.update(pqr)).thenReturn(pqr);
        ResponseEntity response = controller.update(pqr);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pqr.getId(), ((Pqr) response.getBody()).getId());
    }

    @Test
    public void updateTestNotFound() {
        Pqr pqr = new Pqr();
        pqr.setId("123456");
        when(service.update(pqr)).thenReturn(null);
        ResponseEntity response = controller.update(pqr);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
