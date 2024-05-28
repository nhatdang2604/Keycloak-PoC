FROM mysql/mysql-server:8.0

ENV MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD
ENV MYSQL_PORT=$MYSQL_PORT

#Create folder to store mysql bin log for replication
RUN mkdir -p -m 777 /var/log/mysql

ADD my.cnf /etc/mysql/my.cnf

EXPOSE ${MYSQL_PORT}