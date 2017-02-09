package com.github.nllptr.model;

import java.time.LocalDateTime;

public class Flustatus {

    private String status;
    private LocalDateTime fetchTime;

    public Flustatus(String status) {
        this.status = status;
        this.fetchTime = LocalDateTime.now();
    }

    public LocalDateTime getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(LocalDateTime fetchTime) {
        this.fetchTime = fetchTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
