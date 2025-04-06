package com.example.test.testrestapi.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Device {

    private int id;
    private String name;
    private String type;
    private boolean status;
    private Timestamp lastCommunication;


}
