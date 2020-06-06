package com.va181.yogamausadi.model;

import java.util.List;

public class ResponseBuku {
    private String value;
    private String message;
    private List<Buku> result;

    public String getValue(){
        return value;
    }

    public String getMessage(){
        return message;
    }

    public List<Buku> getResult() {
        return result;
    }
}
