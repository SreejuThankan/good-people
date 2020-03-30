#!/bin/bash
echo "Stopping the UI"
cd /var/opt/apps/good-people/pids
ls -rtl
FRONT_PID=$(cat front.pid)
if [ -n $FRONT_PID ]; then
    echo "Process is $FRONT_PID"
    kill -9 $FRONT_PID
else
    echo "No running UI process"
fi