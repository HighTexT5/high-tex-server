package com.uet.hightex.dtos.base;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ResponsePage<T> {
    private T data;
    private int page;
    private int size;
    private int totalElements;
}
