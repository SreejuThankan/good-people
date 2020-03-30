#!/bin/bash
echo "Stopping the service"
cd /var/opt/apps/good-people/pids
ls -rtl
BACK_PID=$(cat back.pid)
if [ -n BACK_PID ]; then
    echo "Process is BACK_PID"
else
    echo "No running service process"
fi