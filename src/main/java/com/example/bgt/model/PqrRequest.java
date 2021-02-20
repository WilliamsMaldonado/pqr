package com.example.bgt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PqrRequest {
    private String username;
    private String text;
    private int type;
    private String relatedId;
}
