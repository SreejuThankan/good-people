#!/bin/bash
echo "Installing UI"
cd /var/opt/apps/good-people/front
ls -rtl
chmod 755 *
ls -rtl
npm install
npm install @angular/cli
ng build
exit 0
# change later