#!/bin/bash
echo "Stopping the UI"
cd /var/opt/apps/good-people/pids
ls -rtl
FRONT_PID=$(cat front.pid)
echo "Previous front process is $FRONT_PID"
kill -9 $FRONT_PID > /var/opt/apps/good-people/logs/stopfront.log 2> /dev/null || :
exit 0