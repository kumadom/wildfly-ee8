# Istio practice

- show istio profiles
  `istioctl profile list`

## EgressGatewayを利用した外部アクセス。

- アウトバウンドの通信制御をレジストリのみに変更。  

  `istioctl install --set profile=demo --set meshConfig.outboundTrafficPolicy.mode=REGISTRY_ONLY`  

- inject sidecar manifest to deployment.yaml  
  `istioctl kube-inject -f deployment.yaml | kubectl apply -f - `
  
- 外部ホストにリクエストが到達できないことを確認  
  
  `kubectl exec -n demo -it wildfly-deployment-7979f5d44-bhhmm -- curl -s -I http://httpbin.org/headers -v`
    `kubectl exec -n demo -it wildfly-deployment-7979f5d44-8hdbc -- curl -s -I http://35.171.190.227/headers -v`

- ServiceEntryのマニフェストを作成し、Istioの内部レジストリに特定のホストを追加  
  
  `kubectl apply -f serviceEntry.yaml`  
  
- リクエストが到達するようになったことを確認  
  `kubectl exec -n demo -it wildfly-deployment-7979f5d44-2n5cx -- curl -s -I -H 'HOST:hoge' http://httpbin.org/headers -v`
  
## Isioのお掃除

`istioctl x uninstall --purge`



curl -OL https://istio.io/downloadIstio


kubectl exec "$(kubectl get pod -l app=ratings -o jsonpath='{.items[0].metadata.name}')" -c ratings -- curl -sS productpage:9080/productpage | grep -o "<title>.*</title>"
kubectl get pod -l app=ratings -o yaml


kubectl exec ratings-v1-96d98f9f6-h8dmq -c ratings -- curl -sS productpage:9080/productpage

 | grep -o "<title>.*</title>"
ratings-v1-96d98f9f6-h8dmq


export INGRESS_NAME=istio-ingressgateway
export INGRESS_NS=istio-system


kubectl get svc istio-ingressgateway -n istio-system -o jsonpath='{.spec.ports[?(@.name=="http2")].port}'


kubectl get svc istio-ingressgateway -n istio-system -o yaml
'


kubectl get svc istio-ingressgateway -n istio-system -o jsonpath='{.spec.ports[?(@.name=="http2")]}'



curl -v -s -I -HHost:httpbin.example.com "http://127.0.0.1:80/productpage"

$ export INGRESS_PORT=$(kubectl -n "$INGRESS_NS" get service "$INGRESS_NAME" -o jsonpath='{.spec.ports[?(@.name=="http2")].port}')

32695












kubectl apply -f samples/bookinfo/networking/bookinfo-gateway.yaml

kubectl -n istio-system edit svc/istio-ingressgateway
kubectl -n istio-system get svc/istio-ingressgateway -o yaml

kubectl get gateways/bookinfo-gateway -o yaml





## IstioのDemoプロファイル
https://github.com/istio/istio/blob/1.18.0/manifests/profiles/demo.yaml

