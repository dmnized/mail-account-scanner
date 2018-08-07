# MAIL ACCOUNT SCANNER
The project was generated as a Maven Project using Spring Initializer at https://start.spring.io/.
A Maven wrapper is included. I worked at this for 23 hours.
## Initialization
The application try to connect to a MySql database as specified in the application.yml.

The database is dropped and created each time the application starts (hibernate.ddl-auto=create in the application.yml).  

Mail Accounts to be scanned must be inserted manually into the database.

The project is based on Spring Boot, so to start the app run the following in the root folder:
`mvnw spring-boot:run`

The application is reachable at http://localhost:8080

Mail messages bytes are stored as files at `mail-account-scanner.mail.folder` path as specified in the application.yml.


## Problems encountered
1. I tried to achieve a composite key for the Mail entity with a generated value and a JoinColumn
2. I tried to use liquibase for database initialization

## Request examples

### To scan a mail account
```bash
curl -X POST \
  http://localhost:8080/api/mail-account-scans \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: c0af9d70-bc88-419b-b1eb-be394d54a9d2' \
  -d '{
	"mailAccountId" : "1",
	"receiptDay" : "20180801"
}'
```
### To search mails
```bash
curl -X GET \
  'http://localhost:8080/api/mails?receiptDay=20180801&mailType=NON_ACCETTAZIONE' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 4f780ff7-0382-4379-aef6-78527f069d22'
```
### To get a mail
```bash
curl -X GET \
  http://localhost:8080/api/mails/1/1 \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 4036670e-2cf6-4a05-b64d-6804e72b4c18'
```    