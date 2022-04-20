# Demo Spring Boot App

### Running the application uses an in-memory H2 database

* Product types from _data.sql_ are loaded on startup
* The file _OnlineStoreApplication.http_ can be run from IntelliJ Idea Ultimate and will execute a number of requests against the running application
* The DbConfiguration @Configuration enables one to connect to the in-memory H2 while the application is running; use _**jdbc:h2:tcp://localhost:9090/mem:online-store**_ url in Database tools inside IntelliJ Idea

### Integration tests use TestContainers with an PostgreSQL database

* Docker must be installed on the machine and running



