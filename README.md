# wildfly-ee8

## deploy
mvn --projects app clean package -Dmaven.test.skip=true
docker build -t wildfly-ee8/sample:0.0 .
kubectl create namespace ee
kubectl apply -f k8s/dev --recursive --namespace=ee
kubectl delete -f k8s/dev --recursive --namespace=ee
kubectl delete namespaces ee

