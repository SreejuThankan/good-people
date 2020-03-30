#!/bin/bash
echo "Stopping the service"
cd /var/opt/apps/good-people/pids
ls -rtl
BACK_PID=$(cat back.pid)
echo "Previous back process is $BACK_PID"
kill -9 $BACK_PID || exit 0