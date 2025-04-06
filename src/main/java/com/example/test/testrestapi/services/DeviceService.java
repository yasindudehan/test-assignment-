package com.example.test.testrestapi.services;

import com.example.test.testrestapi.dto.Device;
import com.example.test.testrestapi.dto.ResponseWrapper;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DeviceService {

  //devices same as in memory database
    private static final Map<Integer, Device> devices  = new ConcurrentHashMap<>();

    public ResponseWrapper<List<Device>> getAllDevices() {
        try{
            List<Device> deviceList = devices.values().stream().toList();
            ResponseWrapper<List<Device>> responseWrapper = new ResponseWrapper<List<Device>>();
           responseWrapper.setCode(200);
            responseWrapper.setMessage("Success");
            responseWrapper.setData(deviceList);
            return responseWrapper;
        }catch(Exception e){
              return internalServerMessage();
        }


    }

    private <T> ResponseWrapper<T> internalServerMessage() {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setCode(500);
        responseWrapper.setMessage("Internal Server Error");
        responseWrapper.setData(null);
        return responseWrapper;
    }

    public ResponseWrapper<Device> getDeviceById(int id) {
        try{
            ResponseWrapper<Device> responseWrapper = new ResponseWrapper<>();

              if(devices.containsKey(id)) {
                  Device device = devices.get(id);
                  responseWrapper.setCode(200);
                  responseWrapper.setMessage("Success");
                  responseWrapper.setData(device);



              }   else{
                  responseWrapper.setCode(400);
                  responseWrapper.setMessage("Device Id not found");
                  responseWrapper.setData(null);
              }
            return responseWrapper;
        }catch(Exception e){
              return internalServerMessage();
        }
    }

    public ResponseWrapper<Device> createDevice(Device device) {
           ResponseWrapper<Device> responseWrapper = new ResponseWrapper<>();
                 try{
                         if(devices.containsKey(device.getId()) ||device.getId()==0) {
                         int lastId=devices.keySet().stream().max(Integer::compare).get();
                         device.setId(lastId+1);
                     }
                        boolean isEmpty = checkEmpty(device);
                        if(isEmpty) {
                            responseWrapper.setCode(400);
                            responseWrapper.setMessage("Device Name and Device Type is required");
                            responseWrapper.setData(null);

                        }else {
                            device.setLastCommunication(device.getLastCommunication() != null ? device.getLastCommunication() : new Timestamp(System.currentTimeMillis()));
                            devices.put(device.getId(), device);
                            responseWrapper.setCode(200);
                            responseWrapper.setMessage("Successfully created");
                            responseWrapper.setData(device);
                        }
                     return responseWrapper;
                 }catch (Exception e){
                   return internalServerMessage();
                 }
    }

    private boolean checkEmpty(Device device) {
         if(Objects.isNull(device.getName()) ||device.getType().isEmpty()) {
             return true;
         }if(Objects.isNull(device.getType()) ||device.getName().isEmpty()) {
             return true;

         }else{
            return false;
        }
    }

    public ResponseWrapper<Device> updateDevice(int id, Device device) {
        try{
            ResponseWrapper<Device> responseWrapper = new ResponseWrapper<>();
            if(devices.containsKey(id)) {
                Device oldDevice = devices.get(id);
                device.setName(checkEmptyNull(device.getName(), oldDevice.getName()));
                device.setType(checkEmptyNull(device.getType(), oldDevice.getType()));
                device.setStatus(device.isStatus()!=oldDevice.isStatus());
                device.setLastCommunication(device.getLastCommunication()!=null?device.getLastCommunication(): oldDevice.getLastCommunication());
                devices.put(id, device);
                responseWrapper.setCode(200);
                responseWrapper.setMessage("Success");
                responseWrapper.setData(device);

            }else{
                responseWrapper.setCode(400);
                responseWrapper.setMessage("Device Id not found");
                responseWrapper.setData(null);
            }

            return responseWrapper;
        } catch (Exception e) {
            return internalServerMessage();
        }
    }

    private static String checkEmptyNull(String device, String oldDevice) {
        if(Objects.isNull(device) || device.isEmpty()) {
            return oldDevice;
        }
        return device;
    }

    public ResponseWrapper<String> deleteDevice(int id) {
        try{
            ResponseWrapper<String> responseWrapper = new ResponseWrapper<>();
            if(devices.containsKey(id)) {
                devices.remove(id);
                responseWrapper.setCode(200);
                responseWrapper.setMessage("Successfully Deleted Device");
                responseWrapper.setData(null);

            }else{
                responseWrapper.setCode(400);
                responseWrapper.setMessage("Device Id not found");
                responseWrapper.setData(null);
            }
            return responseWrapper;
        } catch (Exception e) {
            return internalServerMessage();
        }
    }
}
