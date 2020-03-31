mysqlsh --mysql -u admin -h localhost -C 
pass123
\sql

CREATE DATABASE DEMO;

USER DEMO;

CREATE TABLE EMPLOYEES (
  ID int NOT NULL,
  FIRST_NAME varchar(255) DEFAULT NULL,
  MIDDLE_NAME varchar(255) DEFAULT NULL,
  LAST_NAME varchar(255) DEFAULT NULL,
  SALARY decimal(10,0) DEFAULT NULL,
  SOME_DATE date DEFAULT NULL,
  SOME_TIME time DEFAULT NULL,
  SOME_DATETIME datetime DEFAULT NULL,
  ACTIVE tinyint(1) DEFAULT NULL,
  PRIMARY KEY (ID)
);

Add mysql-connector-java-8.0.19.jar to classpath