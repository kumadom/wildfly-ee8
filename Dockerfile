FROM jboss/wildfly:18.0.0.Final

ADD ./app/server-definition/standalone.xml /opt/jboss/wildfly/standalone/configuration/
# ADD ./app/server-definition/postgresql-42.2.24.jar /opt/jboss/wildfly/standalone/deployments

WORKDIR /opt/jboss/wildfly/standalone/data/content/40/35818e75b14b8b116be8a0ef8f1009073e031e
ADD ./app/server-definition/postgresql-42.2.24.jar ./content

ADD ./app/target/app.war /opt/jboss/wildfly/standalone/deployments/
