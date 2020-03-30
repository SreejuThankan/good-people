#!/bin/bash
echo "Clearing previous payload"
cd /var/opt/apps/good-people/source
echo "Before source cleaning"
ls -rtl
rm -rf *
echo "After source cleaning"
ls -rtl
cd /var/opt/apps/good-people/target
echo "Before target cleaning"
ls -rtl
rm -rf *
echo "After target cleaning"
ls -rtl