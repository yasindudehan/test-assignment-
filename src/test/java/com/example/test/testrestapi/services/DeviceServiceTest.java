package com.example.test.testrestapi.services;

import com.example.test.testrestapi.dto.Device;
import com.example.test.testrestapi.dto.ResponseWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.sql.Timestamp;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;



import static org.mockito.MockitoAnnotations.initMocks;


class DeviceServiceTest {

    @InjectMocks
    DeviceService deviceService;

    @BeforeEach
    void setUp() throws IllegalAccessException, NoSuchFieldException {
        initMocks(this);

    }

    @Test
    void createDevice() {

       var resp= deviceService.createDevice(new Device(1, "test", "test", true, new Timestamp(System.currentTimeMillis())));
        assertNotNull(resp);
    }

    @Test
    void createDevice1() {

        var resp= deviceService.createDevice(new Device(1, "", "", true, new Timestamp(System.currentTimeMillis())));
        assertNotNull(resp);
    }

//    @Test
//    void internalServerMessage() {
//
//
//
//
//    }

    @Test
    void getAllDevices() {


        ResponseWrapper<List<Device>> resp=deviceService.getAllDevices();

        assertNotNull(resp);
    }

    @Test
    void getDeviceById() {
        Device device = new Device();
        device.setId(1);
        device.setName("Device1");
        device.setType("Type1");
        deviceService.createDevice(device);

        ResponseWrapper<Device> resp=deviceService.getDeviceById(1);
        assertNotNull(resp);

    }
    @Test
    void getDeviceById1() {


        ResponseWrapper<Device> resp=deviceService.getDeviceById(100000);
        assertNotNull(resp);

    }


    @Test
    void updateDevice() {
        Device device = new Device();
        device.setId(1);
        device.setName("Device1");
        device.setType("Type1");
        deviceService.createDevice(device);

        var resp=deviceService.updateDevice(1, new Device(1, "test", "test", true, new Timestamp(System.currentTimeMillis())));
        assertNotNull(resp);
    }

    @Test
    void updateDevice1() {
        var resp=deviceService.updateDevice(1, new Device(1, "", "", true, new Timestamp(System.currentTimeMillis())));
        assertNotNull(resp);
    }

    @Test
    void deleteDevice() {
        Device device = new Device();
        device.setId(1);
        device.setName("Device1");
        device.setType("Type1");
        deviceService.createDevice(device);
        var resp=deviceService.deleteDevice(1);
        assertNotNull(resp);
    }

    @Test
    void deleteDevice1() {

        var resp=deviceService.deleteDevice(100000000);
        assertNotNull(resp);
    }
}