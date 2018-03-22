#!/usr/bin/env bash
mvn clean package -DskipTests=true
java -jar target/client2-0.0.1-SNAPSHOT.jar