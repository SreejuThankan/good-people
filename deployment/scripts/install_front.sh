#!/bin/bash
echo "Installing UI"
export PATH="/home/ec2-user/.nvm/versions/node/v13.12.0/bin:/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/home/ec2-user/.local/bin:/home/ec2-user/bin:$PATH"
cd /var/opt/apps/good-people/front
ls -rtl
chmod 755 *
ls -rtl
npm install
npm install @angular/cli
npm run-script ng build
exit 0
# change later