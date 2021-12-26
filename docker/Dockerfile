FROM postgres:11.13-bullseye
ENV POSTGRES_PASSWORD=password \
    POSTGRES_USER=user \
    POSTGRES_DB=postgres
ADD ./postgres/init /docker-entrypoint-initdb.d
