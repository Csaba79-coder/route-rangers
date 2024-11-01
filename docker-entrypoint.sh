#!/bin/sh

while inotifywait -r -e modify /app/src/main/; 
do 
  mvn compile -o -DskipTests; 
done >/dev/null 2>&1 &

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
