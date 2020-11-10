FROM mongo

VOLUME ["/data/db"]

WORKDIR /data

CMD ["mongod"]

EXPOSE 29017:29017