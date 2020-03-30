#!/bin/bash
echo "Checking if service is up"
RESULT=$(curl -s -o /dev/null -w "%{http_code}"  "http://localhost:9000/actuator/health")
if [ "$RESULT" == "200" ]; then
    echo "Application up successfully"
    exit 0
else
    echo "Application failed to start"
    exit 1
fi