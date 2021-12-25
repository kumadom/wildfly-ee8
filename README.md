# wildfly-ee8

## deploy
mvn --projects app clean package -Dmaven.test.skip=true
docker build -t wildfly-ee8/sample:0.0 .
