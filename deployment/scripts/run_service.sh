#!/bin/bash
echo "Running the service"
cd /var/opt/apps/good-people/target
/usr/bin/java -jar service.war > /var/opt/apps/good-people/logs/service.log 2> /var/opt/apps/good-people/logs/serviceerr.log & echo $! > ../pids/service.pid
sleep 30
exit 0