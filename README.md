
# Spring Boot Blog

This is a simple blog application built using Spring Boot. It allows users to create, read, update, and delete blog posts. Users can also leave comments on blog posts and can upvote and downvote for a comments.


## Requirements

To run this application, you will need the following:

* Java 8 or later
* Maven
* MySQL


## Installation



1. Clone this repository to your local machine.

2. Open the project in your favorite IDE (such as IntelliJ or Eclipse).

3. Update the application.properties file in the src/main/resources directory with your MySQL database configuration:
```
spring.datasource.url=jdbc:mysql://localhost:3306/blog
spring.datasource.username=<your_mysql_username>
spring.datasource.password=<your_mysql_password>
```

4. Build the project using Maven: mvn clean install.
5. Run the project: mvn spring-boot:run.
The application will now be available at http://localhost:8080.

    

## Technologies Used


* Spring Boot
* Spring Data JPA
* Thymeleaf
* MySQL
* Spring Security
* Java Mail Sender
* Mapper

## Contributing

Contributions are always welcome!

If you would like to contribute to this project, please submit a pull request.
