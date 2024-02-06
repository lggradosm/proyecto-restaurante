package com.restaurant.coreservice.aggregates.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseBase {
    private int status;
    private String message;
    private Optional data;
}
