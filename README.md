# SPRING BOOT PROJECT TO DEMO RATE LIMITING FILTER

## WHAT IS A RATE LIMITER
A rate limiter is a mechanism that controls how frequently a client can access an API or service over a given time period.

## WHY IS IT USED
It is used to control the amount of requests an application can accept at in particular timeframe.
By controlling the number of requests at any given timeframe we ensure thta the server does not crash due to very high traffic.

## RATE LIMITER USED HERE
**Fixed Window Counter**
Here we have window of fixed size indicating number of requests it can allow within a particular time frame.
If the number of requests go past that threshold they get rejected with **429 Too Many Requests Response**.

``Note:- Every timeframe window can have a fixed but different set of requests count(eg between 10:00 AM to 10:30 AM say you allow 500 requests and from 10:30 AM to 11:30 AM you allow 1000 requests)``.
**🔗 Deep Dive:** [Learn more about the Fixed Window Counter](https://konghq.com/blog/how-to-implement-a-rate-limiting-algorithm#fixed-window)

## OTHER RATE LIMITING ALGORITHMS
Various other rate limiting algorithms include

***

### 1. Sliding Window Log

This method achieves high accuracy and fairness by tracking a **timestamp for every request**. The system calculates the total count over the true last $X$ seconds, regardless of when the window started.

**🔗 Deep Dive:** [Learn more about the Sliding Window Log](https://www.freecodecamp.org/news/how-to-design-a-rate-limiting-system-c5a4d80a187d/#sliding-window-log)

***

### 2. Sliding Window Counter

This algorithm is a space-efficient technique that approximates the Sliding Window Log. It uses a weighted combination of the current fixed window's count and the count from the preceding fixed window.

**🔗 Deep Dive:** [Learn more about the Sliding Window Counter](https://blog.dreamfactory.com/sliding-window-log-vs-sliding-window-counter-for-rate-limiting/)

***

### 3. Token Bucket

Requests consume **tokens** from a virtual "bucket" which is refilled at a **fixed rate**. This allows for traffic bursts (as long as tokens remain) but strictly controls the long-term sustained request rate.

**🔗 Deep Dive:** [Learn more about the Token Bucket Algorithm](https://en.wikipedia.org/wiki/Token_bucket)

***

### 4. Leaky Bucket

This method treats incoming requests as **water filling a bucket** that has a constant **leak rate**. Requests are queued and processed at a uniform rate. If the queue (bucket) overflows, new requests are dropped.

**🔗 Deep Dive:** [Learn more about the Leaky Bucket Algorithm](https://www.cloudflare.com/learning/bots/what-is-leaky-bucket/)

## HOW TO RUN
1) Clone the application into your favourite IDE
(https://github.com/Darshan-Laddha/APIFilters.git)
2) Run the application
   (./mvnw spring-boot:run)
3) The application runs on port 8080 by default, we can change the port through **application.properties** by giving **server.port=<port-desired>**
   or **mvn spring-boot:run -Dserver.port=<port-desired>** while executing the run command
4) Execute the curl **curl --location 'http://localhost:8080/api/hello' \
--header 'X-API-KEY: valid-client'** more than 5 times in 10 seconds window 
   and we should see **Too Many Requests Exception** indicating we too many requests came from the same client and the requests are getting Rate Limited
