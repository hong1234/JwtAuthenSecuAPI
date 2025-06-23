
# JwtAuthenSecuAPI

mvn test // test

mvn spring-boot:run // run

or

// mvn clean package // build jar

java -jar ./target/secu-0.0.1-SNAPSHOT.jar  // run jar file

// ----- Login ---get token --- 

## POST http://localhost:8080/login

// request body JSON 

{
    "username":"admin",
    "password":"admin"
}

or 

{
    "username":"user",
    "password":"user"
}

// --- call API ---

## GET http://localhost:8005/admin/hello

// authorization - authen type Bearer Token - JWT token ...

## GET http://localhost:8005/user/hello

// authorization - authen type Bearer Token - JWT token ...


## GET http://localhost:8080/api/cars

// authorization - authen type Bearer Token - JWT token ...

