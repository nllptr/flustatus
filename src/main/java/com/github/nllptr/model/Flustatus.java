package com.github.nllptr.model;

import java.time.LocalDateTime;

public class FluStatus {

    private String status;
    private LocalDateTime fetchTime;

    public FluStatus(String status, LocalDateTime fetchTime) {
        this.status = status;
        this.fetchTime = fetchTime;
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
