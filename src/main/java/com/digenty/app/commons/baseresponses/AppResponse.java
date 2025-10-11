package com.digenty.app.commons.baseresponses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Builder
public class AppResponse {

    private int status;
    private String message;
    private String supportDescriptiveMessage;
    private Object data;
    private Object error;

    private String traceId;

    public AppResponse(int status, String message, String supportDescriptiveMessage, Object data, Object error, String traceId) {
        this.status = status;
        this.message = message;
        this.supportDescriptiveMessage = supportDescriptiveMessage;
        this.data = data;
        this.error = error;
        this.traceId = traceId;
    }


    public AppResponse() {
    }
}
