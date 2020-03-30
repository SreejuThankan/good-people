#!/bin/bash
echo "Running the UI"
export PATH="/home/ec2-user/.nvm/versions/node/v13.12.0/bin:/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/home/ec2-user/.local/bin:/home/ec2-user/bin:$PATH"
cd /var/opt/apps/good-people/front
ls -rtl
npm run-script ng serve > /var/opt/apps/good-people/logs/front.log 2> /dev/null & echo $! > ../pids/front.pid
exit 0