# Envoy

IstioでEnvoyを利用しているが、EnvoyFilterを利用するさいにEnvoyのアーキテクチャを理解していないとよく動きがわからない部分が発生した。

そのため、Envoyのアーキテクチャ、およびFilterなどのプラグイン機能の理解を行う。

## アーキテクチャ

### Listeners

[Listenerアーキテクチャ](https://www.envoyproxy.io/docs/envoy/latest/intro/arch_overview/listeners/listeners)
[ListenerのAPI](https://www.envoyproxy.io/docs/envoy/latest/configuration/listeners/listeners#config-listeners)

Envoy設定では、複数のリスナーをサポートする。

ネットワークのL3（IP）、L4（Port）のフィルターとして構成される。

### Http Connection Management

Listenerとの違いがよくわからない。

[HTTP Connection Management](https://www.envoyproxy.io/docs/envoy/latest/intro/arch_overview/http/http_connection_management)


### Runtime Configuration

[Runtime Configuration](https://www.envoyproxy.io/docs/envoy/latest/intro/arch_overview/operations/runtime)

## カスタム設定

--config-path オプションでカスタム設定を指定可能。

```
PS>
docker run --rm -it `
      -v "$PWD\:`"C:\envoy-configs`"" `
      -p '9901:9901' `
      -p '10000:10000' `
      'envoyproxy/envoy-windows-dev:c58ccaf8e9276ebd095652e56e89c7d56e92e6c0' `
          --config-path 'C:\envoy-configs\envoy-example.yaml'
```

## オーバーライド設定

```
PS>
docker run --rm -it `
      -p '9902:9902' `
      -p '10000:10000' `
      'envoyproxy/envoy-windows-dev:c58ccaf8e9276ebd095652e56e89c7d56e92e6c0' `
         --config-yaml "$(Get-Content -Raw envoy-override.yaml)" 
```

## Admin用のエンドポイントの開放

[Administration Interface](https://www.envoyproxy.io/docs/envoy/latest/operations/admin#operations-admin-interface)


## Envoyにロードする設定ファイル

Bootstrapのリソースを設定している。

[Bootstrap](https://www.envoyproxy.io/docs/envoy/latest/api-v3/config/bootstrap/v3/bootstrap.proto#envoy-v3-api-field-config-bootstrap-v3-bootstrap-static-resources)


## Router

[Router Filter](https://www.envoyproxy.io/docs/envoy/latest/configuration/http/http_filters/router_filter)

## サンプル


```
PS>
docker run --rm -it `
      -v "$PWD\:`"C:\envoy-configs`"" `
      -p '9901:9901' `
      -p '10000:10000' `
      -p '8080:8080' `
      'envoyproxy/envoy-windows-dev:c58ccaf8e9276ebd095652e56e89c7d56e92e6c0' `
          --config-path 'C:\envoy-configs\envoy-example.yaml'
```

- 検証用
```
PS>
docker run --rm -it `
      -v "$PWD\:`"C:\envoy-configs`"" `
      -p '9901:9901' `
      -p '10000:10000' `
      'envoyproxy/envoy-windows-dev:c58ccaf8e9276ebd095652e56e89c7d56e92e6c0' `
      --mode validate `
          --config-path 'C:\envoy-configs\envoy-example.yaml'
```

## Istioでの設定

### Runtime設定

ProxyConfig.runtimeValuesで設定できそう。

[ProxyConfig](https://istio.io/latest/docs/reference/config/istio.mesh.v1alpha1/#ProxyConfig)

Pod単位での設定も可能に。

[using-proxy-istio-io-config-annotation-for-trace-settings](https://istio.io/latest/docs/tasks/observability/distributed-tracing/mesh-and-proxy-config/#using-proxy-istio-io-config-annotation-for-trace-settings)

