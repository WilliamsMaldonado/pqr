package com.example.bgt.services;

import com.example.bgt.model.Pqr;
import com.example.bgt.model.PqrRequest;
import com.example.bgt.repository.PqrRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PqrServiceImplTest {

    @Mock private PqrRepository repository;

    @InjectMocks private PqrServiceImpl service;

    @Test
    public void savePqrTestOK() throws Exception {
        PqrRequest pqrRequest = new PqrRequest();
        pqrRequest.setUsername("username");
        pqrRequest.setType(3);
        pqrRequest.setRelatedId("relatedID");
        pqrRequest.setText("Text");
        Pqr pqrRelated = new Pqr();
        pqrRelated.setCreateAt(new Date());
        pqrRelated.setReply("Reply");
        when(repository.findById(pqrRequest.getRelatedId())).thenReturn(Optional.of(pqrRelated));
        when(repository.save(Mockito.any())).thenReturn(pqrRelated);
        Pqr pqr = service.savePqr(pqrRequest);
        assertEquals(pqr, pqrRelated);
    }

    @Test
    public void findByIdTestOk() {
        Pqr pqr = new Pqr();
        pqr.setId("ID");
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(pqr));
        Pqr pqr1 = service.findById("id");
        assertEquals(pqr, pqr1);
    }

    @Test
    public void findByUsernameTestOk() {
        when(repository.findByUsername(Mockito.any())).thenReturn(new ArrayList<>());
        List<Pqr> list = service.findByUsername("username");
        assertEquals(0, list.size());
    }

    @Test
    public void findByOpenTestOk() {
        when(repository.findByReply(Mockito.any())).thenReturn(new ArrayList<>());
        List<Pqr> list = service.findByOpen();
        assertEquals(0, list.size());
    }

    @Test
    public void updateTestOk() {
        Pqr pqr = new Pqr();
        pqr.setId("id");
        pqr.setReply("Reply");
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(pqr));
        when(repository.save(Mockito.any())).thenReturn(pqr);
        Pqr pqr1 = service.update(pqr);
        assertEquals(pqr, pqr1);
    }

    @Test
    public void updateTestNull() {
        when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        Pqr pqr1 = service.update(new Pqr());
        assertNull(pqr1);
    }
}
