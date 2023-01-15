package com.example.autoserviceapp.dto.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
