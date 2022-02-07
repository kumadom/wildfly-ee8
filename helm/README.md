# Helm

[参考になりそうなQiita記事](https://qiita.com/thinksphere/items/5f3e918015cf4e63a0bc#helm%E3%83%81%E3%83%A3%E3%83%BC%E3%83%88%E3%81%AE%E9%96%8B%E7%99%BA)

## Helmの起動コマンド

- ローカルのChartをインストール、リリース  
  `helm install helm-demo .\demo`  
  [helm installのドキュメント](https://helm.sh/docs/helm/helm_install/)

- リリースしたChartをアンインストールする  
  `helm uninstall helm-demo`  
  [helm uninstallのドキュメント](https://helm.sh/docs/helm/helm_uninstall/
  
  
## リポジトリ

### Prometheusのリポジトリ

この[URL](https://github.com/prometheus-community/helm-charts)が最新のリポジトリのようだが、
バグが存在するようでinstall後のexporterが起動できない。そのため、以前のリポジトリのchartを利用する。

- [Prometheusのリポジトリ](https://charts.helm.sh/stable)

