FROM postgres:13

RUN mkdir /scripts

WORKDIR /scripts
COPY /scripts/init.sh /docker-entrypoint-initdb.d/
COPY /scripts/asd_activos_bak.dump .

RUN chmod +x /docker-entrypoint-initdb.d/init.sh
RUN chmod +x /scripts/asd_activos_bak.dump
