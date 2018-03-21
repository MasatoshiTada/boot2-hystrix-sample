#!/usr/bin/env bash
mvn clean package -DskipTests=true
java -jar target/hystrix-dashboard-0.0.1-SNAPSHOT.jar