#!/bin/bash
echo "Running the service"
export PATH="/home/ec2-user/.nvm/versions/node/v13.12.0/bin:/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/home/ec2-user/.local/bin:/home/ec2-user/bin:$PATH"
cd /var/opt/apps/good-people/back
java -jar service.war > /var/opt/apps/good-people/logs/back.log 2> /var/opt/apps/good-people/logs/backerror.log & echo $! > ../pids/back.pid
sleep 60
exit 0