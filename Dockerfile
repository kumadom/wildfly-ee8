FROM jboss/wildfly:18.0.0.Final

# 各種ファイル移送
COPY ./deliverables/modules/ ${JBOSS_HOME}/modules/
COPY ./deliverables/configuration/standalone-openshift.xml ${JBOSS_HOME}/standalone/configuration/standalone.xml
COPY ./deliverables/deployments/ ${JBOSS_HOME}/standalone/deployments/

# 文字コード設定
USER root
RUN localedef -f UTF-8 -i ja_JP ja_JP.UTF-8
ENV LANG="ja_JP.UTF-8" \
    LANGUAGE="ja_JP:ja" \
    LC_ALL="ja_JP.UTF-8"
USER jboss

# エントリポイントの設定
ARG ENTRY_POINT_DIR=/opt/entry/
WORKDIR ${ENTRY_POINT_DIR}
COPY ./app/server-definition/dev/cli-commands.txt ${ENTRY_POINT_DIR}
COPY ./app/server-definition/app-entrypoint.sh ${ENTRY_POINT_DIR}
ENTRYPOINT ["/opt/entry/app-entrypoint.sh"]
CMD []

# ログインディレクトリの設定
WORKDIR /opt/jboss/wildfly/
