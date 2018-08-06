## MAIL ACCOUNT SCANNER
The project was generated as a Maven Project using Spring Initializer at https://start.spring.io/.
A Maven wrapper is included.

The application try to connect to a MySql database as specified in the application.yml.

The database is updated each time the application starts (hibernate.ddl-auto= update in the application.yml).  

Mail Account to be scanned must be inserted manually into the database.

The project is based on Spring Boot, so to start the app run the following in the root folder:
`mvnw spring-boot:run`

The application is recheable at http://localhost:8080

The receiptDate must be sent in this format "2017-02-21T13:00:00Z"  to the MailAccountScanResource and the MailResource.

Mail messages bytes are stored at `mail-account-scanner.mail.folder` path as specified in the application.yml.
