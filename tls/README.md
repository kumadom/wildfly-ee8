# TLSの学習メモ

## 鍵のペアを作成

以下の資源を作成する。キーストアとしてPKCS#12の標準が利用され、秘密鍵と証明書が含まれるファイルとして管理される。　　
秘密情報交換標準に則ってキーストアを生成。公開鍵だけでなく秘密鍵なども含まれており、超機密情報。

- 証明書（X.509 v3）形式  
  1. （サーバーの）公開鍵  
  2. 電子署名（1.公開鍵をハッシュ化したものに対してCA暗号鍵で暗号化されたクライアントの検証用ハッシュデータ）　←ない？  
  3. 発行者（CA）識別名：この発行者に対して、CA公開鍵をもらいに行く。  
  　　CA公開鍵をもらいに行く場合には、それはまた証明書として受け取ることになる。この連鎖をPCにプリインストールされているルート証明書にたどり着くまで行う。これを証明書チェーンと言うっぽい。
  
- 秘密鍵

```
keytool -genkeypair　-alias san-wildfly -keyalg RSA -keysize 2048 -sigalg SHA256withRSA -dname "CN=Kumaki Akito, OU=Team Asagaya, O=KUMAKI, L=SUGINAMI, S=TOKYO, C=JP" -validity 365 -storetype PKCS12 -keystore keystore.p12 -storepass donchan -ext "SAN=DNS:localhost"
```
- alias: キーストア上で管理するキー名の指定
- keyalg: 公開鍵と秘密鍵のペアを作成する際に使うアルゴリズムの指定
- keysize: 鍵のサイズ
- sigalg: 署名作成のアルゴリズム
- dname: X.500 識別名の指定  
  - CN=commonName - 人の通称
  - OU=organizationUnit - 小さな組織 (部、課など) の名称。 「仕入部」など
  - O=organizationName - 大きな組織の名称。 「ABCSystems, Inc.」など
  - L=localityName - 地域 (都市) 名。 「Palo Alto」など
  - S=stateName - 州名または地方名。 「California」など
  - C=country - 2 文字の国番号。 「CH」など
- storetype: キーストアのタイプの指定
- keystore: キーストアの場所
- storepass: キーストアのパスワード

## キーストアの確認

```
keytool -list -v -keystore .\keystore.p12
```


## 証明書の抽出

```
keytool -exportcert -keystore .\keystore.p12 -alias san-wildfly -file san.cer
```

## 証明書の表示

```
keytool -printcert -file .\san.cer
```

## 証明書の拡張領域のSAN(サブジェクトの別名)

要は複数のCommonNameを持つことができるような拡張領域のこと。X.509形式の拡張。
このサブジェクトの別名を持つ証明書のことをSAN証明書と言ったりもする。
RFC2818では通常のSSL証明書の代わりにSAN証明書を利用することを推奨している。

利用可能な項目は、DNS（DNS名）、IP（IPアドレス）、EMAIL（メールアドレス）。
らしい。（RFC5280で規定されている模様）カンマつなぎ。

