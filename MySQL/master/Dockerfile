FROM mysql/mysql-server:8.0

ENV MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD

# #Create folder to store mysql bin log for replication
RUN mkdir -p -m 700 /var/log/mysql
RUN chown -R mysql:mysql /var/log/mysql

ADD my.cnf /etc/my.cnf

EXPOSE 3306