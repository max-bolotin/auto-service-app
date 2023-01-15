# Auto Service Application
___
This is the RESTful API for a simple auto service. It makes possible to register workers, servicings and products, and also register clients of the service and their cars. The application allows calculating the order price (including servicings and products as well as client's personal discount which depends on his/her orders number) and the workers' salary.
___
## Technologies stack:
- Java 11
- Spring Boot 5.6.0
- Maven
- PostgreSQL
- Lombock
- Liquibase
- REST
___
## Instructions
- Use [Swagger](http://localhost:8080/swagger-ui/#) to control the application
- First save at least one worker
- Save at least one servicing. Servicing No.1 in each order will be the 'Diagnostics'. The client won't be charged for the 'Diagnostics' in case he/she orders more servicings
- (Optional) Save at least one product
- Save the new car
- Save the new client (the car's owner)
- Add client to the car (important!)
- Create the order for the car with at least one servicing ('Diagnostics') included
- (Optional) Add another servicing and/or product. Add as many servicings and products as needed
- Add worker to the servicing
- Add order to the client
- Add order to the worker
- Calculate the total price of the order
- Change the order status to 'FULFILLEDSUCCESSFULLY'
- Calculate the worker's salary
- Change the order status to 'PAID'

