# wildfly-ee8

## deploy

mvn --projects app clean package -Dmaven.test.skip=true

docker build -t wildfly-ee8/sample:0.0 .

docker build -t db-ee8/sample:0.0 .db-Dockerfile

kubectl create namespace ee

kubectl apply -f k8s/dev --recursive --namespace=ee

kubectl delete -f k8s/dev --recursive --namespace=ee

kubectl delete namespaces ee

## kustomize


## Openshift

- BuildConfigのソース（入力内容）設定にバイナリーソースを設定した場合のビルド
`oc replace --force -f https://raw.githubusercontent.com/jboss-container-images/jboss-eap-openshift-templates/eap74/eap74-openjdk8-image-stream.json`

`oc new-build jboss-eap74-openjdk8-openshift:7.4.0~https://github.com/kumadom/wildfly-ee8.git --context-dir deliverables`

`oc new-app wildfly-ee8 -e DB_SERVICE_PREFIX_MAPPING=app-postgresql=DB -e DB_JNDI=java:/PostgresDS -e DB_DATABASE=postgres  -e DB_NONXA=true -e DB_XA_CONNECTION_PROPERTY_postgres=hoge -e DB_USERNAME=user -e DB_PASSWORD=password -e DB_DRIVER=postgresql-42.2.24.jar -e DB_URL='jdbc:postgresql://localhost:5432/postgres'`

ビルド言語の検出手順を回避する方法"~"の指定
https://access.redhat.com/documentation/ja-jp/openshift_container_platform/4.5/html/applications/creating-applications-using-cli


- ビルダーイメージを利用したEEサーバーのビルド、デプロイ

`oc replace --force -f https://raw.githubusercontent.com/jboss-container-images/jboss-eap-openshift-templates/eap74/eap74-openjdk8-image-stream.json`

`oc new-build jboss-eap74-openjdk8-openshift:7.4.0 https://github.com/kumadom/wildfly-ee8.git`

`oc new-app wildfly-ee8 -e DB_SERVICE_PREFIX_MAPPING=app-postgresql=DB -e DB_JNDI=java:/PostgresDS -e DB_DATABASE=postgres  -e DB_NONXA=true -e DB_XA_CONNECTION_PROPERTY_postgres=hoge -e DB_USERNAME=user -e DB_PASSWORD=password -e DB_DRIVER=postgresql-42.2.24.jar -e DB_URL='jdbc:postgresql://localhost:5432/postgres'`

data-source add --name=PostgresDS --jndi-name=java:/PostgresDS --driver-class=org.postgresql.Driver --driver-name=postgresql-42.2.24.jar --user-name=user --password=password --valid-connection-checker-class-name=org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker --background-validation=true --exception-sorter-class-name=org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter --connection-url=jdbc:postgresql://localhost:5432/postgres