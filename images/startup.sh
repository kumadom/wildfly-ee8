#!/bin/bash

cp /layers/buildpacks_empty/app/app.war /opt/jboss/wildfly/wildfly-27.0.1.Final/standalone/deployments
sh /opt/jboss/wildfly/wildfly-27.0.1.Final/bin/standalone.sh

