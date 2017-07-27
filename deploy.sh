#!/bin/sh

/usr/local/payara41/bin/asadmin undeploy snowgirl-1.0-SNAPSHOT
/usr/local/payara41/bin/asadmin deploy --contextroot=/ ./build/libs/snowgirl-1.0-SNAPSHOT.war