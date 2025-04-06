package com.example.test.testrestapi.controlles;

import com.example.test.testrestapi.dto.ResponseWrapper;
import org.springframework.http.ResponseEntity;

public class BaseController {


       public <T> ResponseEntity<ResponseWrapper<T>> responseHandler(ResponseWrapper t) {

             switch (t.getCode()) {
                 case 200:
                     return ResponseEntity.ok(t);
                 case 400:
                     return ResponseEntity.badRequest().body(t);
                 default:
                     return ResponseEntity.internalServerError().body(t);

             }


       }
}
