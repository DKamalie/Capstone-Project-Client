package com.example.application.api;

import com.example.application.domain.Vehicle;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
public class VehicleApi {
    static RestTemplate restTemplate = new RestTemplate();

    public Integer vehicleId;
    public String vehicleType;
    public String make;
    public String model;
    public String year;
    public String colour;

    private static String vehicleUrl = "http://localhost:8080/vehicle/";


    public Vehicle createVehicle(Vehicle vehicle){
        vehicleId = vehicle.getVehicleId();
        vehicleType = vehicle.getVehicleType();
        make = vehicle.getMake();
        model = vehicle.getModel();
        year = vehicle.getYear();
        colour = vehicle.getColour();

        HttpEntity<Vehicle> request = new HttpEntity<>(new Vehicle(
           vehicleId,
           vehicleType,
           make,
           model,
           year,
           colour
        ));

        Vehicle created = restTemplate.postForObject(vehicleUrl + "/create", request, Vehicle.class);
        System.out.println(vehicle.toString());
        return created;
    }


    public Vehicle readVehicle(Integer vehicleId){
        Vehicle read = restTemplate.getForObject(vehicleUrl + "/read/" + vehicleId, Vehicle.class);
        System.out.println(read.toString());
        return read;
    }

    public String updateVehicle(Vehicle vehicle){
        vehicleId = vehicle.getVehicleId();
        vehicleType = vehicle.getVehicleType();
        make = vehicle.getMake();
        model = vehicle.getModel();
        year = vehicle.getYear();
        colour = vehicle.getColour();

        HttpEntity<Vehicle> request = new HttpEntity<>(new Vehicle(
                vehicleId,
                vehicleType,
                make,
                model,
                year,
                colour
        ));
        Vehicle updated = restTemplate.postForObject(vehicleUrl + "/update",request, Vehicle.class);
        System.out.println(vehicle.toString());

        return "The updated Vehicle is: " + updated;
    }


    public void deleteVehicle(Integer id){
    String entityUrl = vehicleUrl + "/delete/" + id;
    System.out.println("In Api" + entityUrl);
    restTemplate.delete(entityUrl);
    }

    public Set<Vehicle> getAll(){
        String apiUrl = vehicleUrl + "/getall";
        ResponseEntity<Vehicle[]> responseEntity = restTemplate.getForEntity(apiUrl, Vehicle[].class);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            Vehicle[] vehicle = responseEntity.getBody();
            return new HashSet<>(Arrays.asList(vehicle));
        }else{
            return Collections.emptySet();
        }
    }
}
