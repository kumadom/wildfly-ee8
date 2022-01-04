FROM jboss/wildfly:18.0.0.Final

ADD ./app/server-definition/standalone.xml /opt/jboss/wildfly/standalone/configuration/
ADD ./app/modules/postgresql-42.2.24.jar /opt/jboss/wildfly/standalone/modules/com/postgresql/main/

# WORKDIR /opt/jboss/wildfly/standalone/data/content/40/35818e75b14b8b116be8a0ef8f1009073e031e
# ADD ./app/server-definition/postgresql-42.2.24.jar ./content

ADD ./app/target/app.war /opt/jboss/wildfly/standalone/deployments/

USER root
RUN localedef -f UTF-8 -i ja_JP ja_JP.UTF-8
ENV LANG="ja_JP.UTF-8" \
    LANGUAGE="ja_JP:ja" \
    LC_ALL="ja_JP.UTF-8"
USER jboss

# エントリポイントの設定を行います
ENV ENTRY_POINT_DIR=/opt/entry/
WORKDIR ${ENTRY_POINT_DIR}
ADD ./app/server-definition/dev/cli-commands.txt ${ENTRY_POINT_DIR}
ADD ./app/server-definition/app-entrypoint.sh ${ENTRY_POINT_DIR}
ENTRYPOINT ["/opt/entry/app-entrypoint.sh"]
CMD []
WORKDIR /opt/jboss/wildfly/
