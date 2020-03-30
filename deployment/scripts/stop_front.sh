#!/bin/bash
echo "Stopping the UI"
cd /var/opt/apps/good-people/pids
ls -rtl
FRONT_PID=$(cat front.pid)
echo "Previous front process is $FRONT_PID"
kill -9 $FRONT_PID
exit 0