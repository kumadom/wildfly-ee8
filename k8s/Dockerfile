FROM jboss/wildfly:18.0.0.Final

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
COPY ./random.sh ${ENTRY_POINT_DIR}
ENTRYPOINT ["/bin/sh"]
CMD ["/opt/entry/random.sh"]
