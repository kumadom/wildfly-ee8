# Istio practice

- show istio profiles
  `istioctl profile list`

## EgressGatewayを利用した外部アクセス。

- アウトバウンドの通信制御をレジストリのみに変更。  

  `istioctl install --set profile=demo --set meshConfig.outboundTrafficPolicy.mode=REGISTRY_ONLY`  

- inject sidecar manifest to deployment.yaml
  `istioctl kube-inject -f deployment.yaml > kubectl apply -f - `
  
- 外部ホストにリクエストが到達できないことを確認  
  
  `kubectl exec -n demo -it wildfly-deployment-7979f5d44-fdlvd -- curl -s -I http://httpbin.org/headers -v`

- ServiceEntryのマニフェストを作成し、Istioの内部レジストリに特定のホストを追加  
  
  `kubectl apply -f serviceEntry.yaml`  
  
- リクエストが到達するようになったことを確認  
  `kubectl exec -n demo -it wildfly-deployment-7979f5d44-fdlvd -- curl -s -I -H 'HOST:hoge' http://httpbin.org/headers -v`
  
## Isioのお掃除

`istioctl x uninstall --purge`
