# Flight-API Publisher
Requirements
- 
1. docker
2. docker-compose

How to Run Publisher API?
-
1. deploy app-data kafka, zookeeper, and redis
    > cd app-data
    > docker-compose up -d
2. export local env
    > export HOST_KAFKA=localhost
    > export HOST_KAFKA=localhost
3. deploy publisher api
	> mvn clean install
	> java -jar target/publisher-0.0.1-SNAPSHOT.jar

Documentation
-
#### Login

Used to collect a Token for a registered User.

**URL** : `/v1/login`

**Method** : `POST`

**Auth required** : NO

**Data example**

```json
{
    "username": "tester1",
    "password": "tester1"
}
```

## Success Response

**Code** : `200 OK`

**Header example**

```
Authorization = Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NjcwMTU2NjAsImhhc2giOiJjMTdmODI3ZjcyODgzNjY1MDVhOWE2MmQ0MTY3ZWQ3NiIsInVzZXJuYW1lIjoidGVzdGVyMSJ9.LWPkjwjRrGGjkC32VueDa0Dm3ObnVvL_CjIao4J6hmJQaDElv0at0_kvDq5I7dRFaR6xWgkktMpdhYNectga4w
```

#### Logout

Used to logout using registered token.

**URL** : `/v1/logout`

**Method** : `POST`

**Header**
```
Authorization = Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NjcwMTU2NjAsImhhc2giOiJjMTdmODI3ZjcyODgzNjY1MDVhOWE2MmQ0MTY3ZWQ3NiIsInVzZXJuYW1lIjoidGVzdGVyMSJ9.LWPkjwjRrGGjkC32VueDa0Dm3ObnVvL_CjIao4J6hmJQaDElv0at0_kvDq5I7dRFaR6xWgkktMpdhYNectga4w
Content-Type = application/json
```

## Success Response

**Code** : `200 OK`

**Body** :
```
logout success
```

#### Add Airline

Add New Airlines

**URL** : `/v1/airlines/`

**Method** : `POST`

**Header**
```
Authorization = Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NjcwMTU2NjAsImhhc2giOiJjMTdmODI3ZjcyODgzNjY1MDVhOWE2MmQ0MTY3ZWQ3NiIsInVzZXJuYW1lIjoidGVzdGVyMSJ9.LWPkjwjRrGGjkC32VueDa0Dm3ObnVvL_CjIao4J6hmJQaDElv0at0_kvDq5I7dRFaR6xWgkktMpdhYNectga4w
Content-Type = application/json
```

**Body Example**
```json
{
	"code":"LION",
	"name": "LION YK-CKG",
	"status":"INACTIVE"
}
```

## Success Response

**Code** : `200 OK`

**Body** :
```
Create Airline submitted
```

#### Update Airline

UPDATE New Airlines

**URL** : `/v1/airlines/{id}`

**Method** : `PUT`

**Header**
```
Authorization = Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NjcwMTU2NjAsImhhc2giOiJjMTdmODI3ZjcyODgzNjY1MDVhOWE2MmQ0MTY3ZWQ3NiIsInVzZXJuYW1lIjoidGVzdGVyMSJ9.LWPkjwjRrGGjkC32VueDa0Dm3ObnVvL_CjIao4J6hmJQaDElv0at0_kvDq5I7dRFaR6xWgkktMpdhYNectga4w
Content-Type = application/json
```

**Body Example**
```json
{
	"code":"LION",
	"name": "LION YK-CKG",
	"status":"INACTIVE"
}
```

## Success Response

**Code** : `200 OK`

**Body** :
```
Update Airline submitted
```