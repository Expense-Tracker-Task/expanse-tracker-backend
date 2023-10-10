package com.timurturbil.expansetrackerbackend.dto;

import lombok.Data;

@Data //includes Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
public class Response<T> {

    private String status;
    private String message;
    private T data;

    public Response(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
