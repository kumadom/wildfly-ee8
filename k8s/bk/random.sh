#!/bin/sh
i=0
while [[ $i -lt 100000 ]]
do
  RESULT=`cat /dev/urandom | tr -dc 'A-Za-z0-9' | fold -w 8 | head -n 1`
  echo -e "$RESULT" >> /opt/entry/logs/random_result.text
  i=`expr $i + 1`
done
