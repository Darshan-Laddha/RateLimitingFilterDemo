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
