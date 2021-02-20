package com.example.bgt.model;


import com.example.bgt.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Pqr {
    @Id
    private String id;

    @Indexed
    private String username;

    private Date createAt;

    private String text;

    private TypePqr typePqr;

    private String relatedId;

    private String reply;

    private Date updateAt;

    public static Pqr getPqr(PqrRequest pqrRequest, Pqr pqrRelated) throws Exception {
        if (pqrRequest.getUsername() == null || pqrRequest.getUsername().isEmpty()) {
            throw new Exception(Constants.ERROR_USERNAME);
        }
        if (pqrRequest.getText() == null || pqrRequest.getText().isEmpty()) {
            throw new Exception(Constants.ERROR_TEXT);
        }
        TypePqr typePqr = new TypePqr();
        typePqr.setId(pqrRequest.getType());
        switch (pqrRequest.getType()) {
            case Constants.PETICION:
                typePqr.setName(Constants.PETICION_NAME);
                break;
            case Constants.QUEJA:
                typePqr.setName(Constants.QUEJA_NAME);
                break;
            case Constants.RECLAMO:
                if (pqrRelated == null) {
                    throw new Exception(Constants.ERROR_RELATED_ID);
                }
                typePqr.setName(Constants.RECLAMO_NAME);
                break;
            default:
                throw new Exception(Constants.ERROR_TYPE);
        }
        return new Pqr.PqrBuilder().username(pqrRequest.getUsername()).text(pqrRequest.getText()).typePqr(typePqr)
                .createAt(new Date()).updateAt(new Date()).relatedId(pqrRequest.getRelatedId()).build();
    }
}
