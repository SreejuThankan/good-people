#!/bin/bash
echo "Stopping the service"
cd /var/opt/apps/good-people/pids
ls -rtl
SERVICE_PID=$(cat service.pid)
echo "Previous back process is $SERVICE_PID"
kill -9 $SERVICE_PID > /var/opt/apps/good-people/logs/stopservice.log 2> /dev/null || :
exit 0