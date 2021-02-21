package com.example.bgt.model;

import com.example.bgt.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PqrTest {

    @Test(expected = Exception.class)
    public void getPqrErrorUsername() throws Exception {
        PqrRequest pqrRequest = new PqrRequest();
        Pqr pqr = Pqr.getPqr(pqrRequest, null);
    }

    @Test(expected = Exception.class)
    public void getPqrErrorText() throws Exception {
        PqrRequest pqrRequest = new PqrRequest();
        pqrRequest.setUsername("username");
        Pqr pqr = Pqr.getPqr(pqrRequest, null);
    }

    @Test()
    public void getPqrPeticionOk() throws Exception {
        PqrRequest pqrRequest = new PqrRequest();
        pqrRequest.setUsername("username");
        pqrRequest.setText("Text");
        pqrRequest.setType(1);
        Pqr pqr = Pqr.getPqr(pqrRequest, null);
        assertEquals(pqrRequest.getUsername(), pqr.getUsername());
        assertEquals(pqrRequest.getText(), pqr.getText());
        assertEquals(pqrRequest.getType(), pqr.getTypePqr().getId());
        assertEquals(Constants.PETICION_NAME, pqr.getTypePqr().getName());
        assertNotNull(pqr.getCreateAt());
        assertNotNull(pqr.getUpdateAt());
    }

    @Test()
    public void getPqrQuejaOk() throws Exception {
        PqrRequest pqrRequest = new PqrRequest();
        pqrRequest.setUsername("username");
        pqrRequest.setText("Text");
        pqrRequest.setType(2);
        Pqr pqr = Pqr.getPqr(pqrRequest, null);
        assertEquals(pqrRequest.getUsername(), pqr.getUsername());
        assertEquals(pqrRequest.getText(), pqr.getText());
        assertEquals(pqrRequest.getType(), pqr.getTypePqr().getId());
        assertEquals(Constants.QUEJA_NAME, pqr.getTypePqr().getName());
        assertNotNull(pqr.getCreateAt());
        assertNotNull(pqr.getUpdateAt());
        assertNotNull(pqr.getTypePqr().toString());
    }

    @Test(expected = Exception.class)
    public void getPqrReclamoError() throws Exception {
        PqrRequest pqrRequest = new PqrRequest("username", "test", 3, "relatedId");
        Pqr pqr = Pqr.getPqr(pqrRequest, null);
    }

    @Test(expected = Exception.class)
    public void getPqrDefaultError() throws Exception {
        PqrRequest pqrRequest = new PqrRequest();
        pqrRequest.setUsername("username");
        pqrRequest.setText("Text");
        pqrRequest.setType(5);
        Pqr pqr = Pqr.getPqr(pqrRequest, null);
    }

    @Test
    public void pqrBuilderOk() {
        String pqrBuilder = new Pqr.PqrBuilder().id("ID").reply("Reply").toString();
        assertNotNull(pqrBuilder);
    }
}
