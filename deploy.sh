#!/bin/sh

echo "undeploy start"
/usr/local/payara41/bin/asadmin undeploy snowgirl
if [ $? -eq 0 ]; then
    echo "undeploy success"
fi
echo "deploy start"
/usr/local/payara41/bin/asadmin deploy --contextroot=/ ./build/libs/snowgirl.war
if [ $? -eq 0 ]; then
    echo "deploy success"
fi