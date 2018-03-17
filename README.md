Library managment system

Spring Application with MySQL Database. 
We used Hibernate as a data mediator.
Thymeleaf as a views provider.

Features of our project:

-creating user accounts

-searching book in library

-borrowing books

-giving back books

-calculating bill after date of return is earlier than local date

-notifications in User Panel with Observer Design Pattern

Setup:

-MySQL Database is needed running on port 3306 . You can change username and password to database in application.properties file
PATH(src/main/resources/application.properties)

-the simplest way to run appliction is using InteliJ Ultimate Edition which have embedded TomcatServer and click run on LibraryApplicaiton.java

-in other ways we need to install Tomcat manually

-after that insert records from setup.sql  PATH(src/main/resources/setup.sql)


