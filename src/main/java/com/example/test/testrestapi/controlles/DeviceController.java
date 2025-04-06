package com.example.test.testrestapi.controlles;

import com.example.test.testrestapi.dto.Device;
import com.example.test.testrestapi.dto.ResponseWrapper;
import com.example.test.testrestapi.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController extends BaseController {


    @Autowired
    private DeviceService deviceService;

    @GetMapping("/all")
    public ResponseEntity<ResponseWrapper<List<Device>>> getAllDevices() {

           ResponseWrapper<List<Device>> responseWrapper = deviceService.getAllDevices();
          return  responseHandler(responseWrapper);


    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Device>> getDeviceById(@PathVariable("id") int id) {

        ResponseWrapper<Device> responseWrapper = deviceService.getDeviceById(id);
        return  responseHandler(responseWrapper);
    }


     @PostMapping("/create")
     public ResponseEntity<ResponseWrapper<Device>> createDevice(@RequestBody Device device) {

         ResponseWrapper<Device> responseWrapper= deviceService.createDevice(device);
         return  responseHandler(responseWrapper);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseWrapper<Device>> updateDevice(@PathVariable("id") int id,@RequestBody Device device) {
        ResponseWrapper<Device> responseWrapper=  deviceService.updateDevice(id,device);
        return  responseHandler(responseWrapper);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper<String>> deleteDevice(@PathVariable("id") int id) {
        ResponseWrapper<String> responseWrapper=  deviceService.deleteDevice(id);
        return  responseHandler(responseWrapper);

    }

}
