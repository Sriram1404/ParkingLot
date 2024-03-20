Parking Lot

    This web service is responsible for automatic management of allocating the parking slot to car in parking lot during request.
    Once the car veccate from parking lot, system will make that spot available for future request.  

Project Requirements:

    JDK 17
    Maven 3
    Spring Boot 3

Running the application locally:

    mvn spring-boot: run

Docker 

    build: docker build -t parking-lot:1 .
    
    run: docker run -p 8080:8080 parking-lot:1

Swagger Endpoint:
    
    http://localhost:8080/swagger-ui.html

Health Check Endpoint:
    
    http://localhost:9001/actuator/health

Parking Lot Endpoints:

1) Create a parking lot with given capacity.
```
    Method: POST
    Endpoint: http://localhost:8080/parking-service/v1/parking-lot/
```
2) Assign parking slot.
```
    Method: POST
    Endpoint: http://localhost:8080/parking-service/v1/parking-lot/assign-parking-slot
```
3) Release parking slot.
```
   Method: DELETE 
   Enpoint: http://localhost:8080/parking-service/v1/parking-lot/release-parking-slot/{slotId}
```
4)  Get car registration numbers by car color. 
```
    Method: GET
    Endpoint: http://localhost:8080/parking-service/v1/parking-lot/cars/color/{carColor}/registered-numbers
```
5) Get parking slot by car registration number.
```
    Method: GET
    Endpoint: http://localhost:8080/parking-service/v1/parking-lot/cars/registration-number/{registeredNumber}/parking-slot
```
6) Get all parking slots by car color.
```
Method: GET
Endpoint: http://localhost:8080/parking-service/v1/parking-lot/cars/color/{carColor}/parking-slots
```
7) Get all occupied parking slots.
```
Method: GET
Endpoint: http://localhost:8080/parking-service/v1/parking-lot/parking-slots
```

