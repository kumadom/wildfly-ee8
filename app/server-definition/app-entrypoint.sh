#!/bin/sh

# TODO: 直したい
# CLIコマンドのテキストファイルを実行します
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -c=standalone.xml &
sleep 10
/opt/jboss/wildfly/bin/jboss-cli.sh --connect --file=/opt/entry/cli-commands.txt

# 設定が完了したので、バックグラウンドで動作しているサーバー処理を停止
/opt/jboss/wildfly/bin/jboss-cli.sh --connect --command=:shutdown
# JBOSSサーバーの起動を行います
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -c=standalone.xml
