#!/bin/bash
echo "Running the service"
cd /var/opt/apps/good-people/back
/usr/bin/java -jar service.war & echo $! > ../pids/back.pid