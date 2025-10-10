# SPRING BOOT PROJECT TO DEMO RATE LIMITING FILTER

## WHAT IS A RATE LIMITER
A rate limiter is a mechanism that controls how frequently a client can access an API or service over a given time period.

## WHY IS IT USED
It is used to control the amount of requests an application can accept at in particular timeframe.
By controlling the number of requests at any given timeframe we ensure thta the server does not crash due to very high traffic.

## HOW TO RUN
1) Clone the appplication into your favourite IDE
(https://github.com/Darshan-Laddha/APIFilters.git)
2) Run the application
   (./mvnw spring-boot:run)
3) The application runs on port 8080 by default, we can change the port through **application.properties** by giving **server.port=<port-desired>**
   or **mvn spring-boot:run -Dserver.port=8083** while executing the run command
4) Execute the API http://localhost:8080/api/hello with header as X-API-KEY=valid-client more than 5 times in 10 seconds window 
   and we should see Too Many Requests Exception indicating we too many requests came from the same client
