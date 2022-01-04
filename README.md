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

- ビルダーイメージを利用したEEサーバーのビルド、デプロイ

`oc replace --force -f https://raw.githubusercontent.com/jboss-container-images/jboss-eap-openshift-templates/eap74/eap74-openjdk8-image-stream.json`

`oc new-build jboss-eap74-openjdk8-openshift:7.4.0 https://github.com/kumadom/wildfly-ee8.git --context-dir=app`

`oc start-build wildfly-ee8`
