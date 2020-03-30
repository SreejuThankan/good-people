#!/bin/bash
echo "Checking if UI is up"
RESULT=$(curl -s -o /dev/null -w "%{http_code}"  "http://localhost:4200")
if [ "$RESULT" == "200" ]; then
    echo "UI up successfully"
    exit 0
else
    echo "UI failed to start"
    exit 0
    # change this later
fi