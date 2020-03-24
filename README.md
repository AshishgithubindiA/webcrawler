# webcrawler

Assumptions:

- Url length has been set to a limit 300 to manage with in memory data store.
- This version is just to read public data of goodreads.com
- We can scale it for other websites by creating more helpers.
- not convenient for secure sites.
- scrapping only book details from goodreads.com. scaling details given below

Libraries used:

- Jsoup: As HTML document reader.
- ActiveMQ:  For in memory asynchronous url storing and processing.
- h2database: InMemory data store. We can configure others based on different scale and requirements.

1. Can this crawler be made even more generic, i.e. can it handle other sites with different html layouts?
- Yes, its generic. Just we have to add site specific handlers by observing the nature of relevant html
elements and specific data model objects. All the scrapping code are reusable. I have done it with goodreadshandler.

2. Are there any sites you cannot crawl?
- yes we can crawl any website here. But if we act like bot and keep hitting any secure servers for long time, your ip
can get blocked by proxy server. There are options to overcome this some way by using proxycrawl, puppeteer like apis.

3. How would you scale this crawler so it can crawl millions of website?
- We can use proper distributed architecture by using
    s3: distributed file system to store documents
    Caching: Redis like caching mechanism for faster read heavy checks.
    Multi service replicas: fault-tolerance and traffic distribution for high scalability.
    Data Sharding: for data distribution and high throughput/distribution.
    etc.

4. How would you store all the different data?
- S3: for document store as distributed file system.
- Nosql like MongoDb :  to store lrge scale scraped data.
- Redis like cache : to quickly verify urls whether its visited or duplicate or any read heavy check.

6. Explain the data model
- Right now not any composed model used. Using simple url visiting status table and bookinfo table not regarding relation.

7. Explain the design patterns used
- I have used factory design pattern to get specific handler. Like, In here if url contains goodreads.com,
 all the scrapping should be done by goodreads handler. if not match any, we can manage it by general website handler.


How to run:

Linux command: java -jar target/webcrawler-1.0-SNAPSHOT.jar

Then you will be able to pass url through swagger UI

link : http://localhost:8080//swagger-ui.html#!/
