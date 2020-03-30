#!/bin/bash
echo "Clearing previous payload"
cd /var/opt/apps/good-people/source
echo "Before source cleaning"
ls -rtl
rm -rf *
echo "After source cleaning"
ls -rtl
cd /var/opt/apps/good-people/front
echo "Before front cleaning"
ls -rtl
rm -rf *
echo "After front cleaning"
ls -rtl
cd /var/opt/apps/good-people/back
echo "Before back cleaning"
ls -rtl
rm -rf *
echo "After back cleaning"
ls -rtl