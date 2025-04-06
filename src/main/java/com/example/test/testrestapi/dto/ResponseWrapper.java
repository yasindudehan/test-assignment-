package com.example.test.testrestapi.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseWrapper<T> {

         private int code;
         private String message ;
         private T data;
}
