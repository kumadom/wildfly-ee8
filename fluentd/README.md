# Fluentd

- gitからfluentdのデーモンセットのコンテナイメージを取得。

```
https://github.com/fluent/fluentd-kubernetes-daemonset
```

- Dockerイメージのビルド

syslogを利用する場合

```
docker build -t fluent/fluentd-kubernetes-daemonset:v1-debian-syslog .
```

- kubernetesのDaemonSetとして起動

```
kubectl apply -f ./log_collector.yaml
```

## サンプルでメッセージを解析させる場合

```
cat << EOF >> hoge.log
{"key":"sample Message","time":"2017-01-12T14:12:13.136621898Z"}
EOF
```

