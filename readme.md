# Network

Create a network between Keycloak and its db (PostgreSQL)

```
docker network create keycloak-postgres
```

# Postgres for Keycloak

```
docker pull postgres:16.2 
```

```
docker run \
    --rm \
    --network keycloak-postgres \
    --name keycloakdb \
    --env POSTGRES_USER=keycloak \
    --env POSTGRES_PASSWORD=keycloak \
    --publish 5432:5432 \
    --volume /data:/var/lib/postgresql/data \
    --detach \
    postgres:16.2
```

# Keycloak
```
docker build ./Keycloak -t keycloak-poc
```

```
docker run \
    --rm \
    --network keycloak-postgres \
    --name keycloak-instance \
    --publish 8443:8443 \
    --publish 8080:8080 \
    --env KEYCLOAK_ADMIN=admin \
    --env KEYCLOAK_ADMIN_PASSWORD=admin \
    --env KC_DB=postgres \
    --env KC_DB_URL=jdbc:postgresql://keycloakdb:5432/postgres \
    --env KC_DB_USERNAME=keycloak \
    --env KC_DB_PASSWORD=keycloak \
    --env KC_HOSTNAME=localhost \
    --detach \
    keycloak-poc \
    start-dev
```

# MySQL for App
## Build & Run MySQL
```
docker pull mysql/mysql-server:8.0
```

```
docker run \
    --rm \
    --name appdb \
    --publish 3306:3306 \
    --env MYSQL_ROOT_PASSWORD=admin \
    --volume  mysql:/var/lib/mysql \
    --detach \
    mysql/mysql-server:8.0
```

## Create an account for app and grant permissions
```
CREATE USER 'app'@'%' IDENTIFIED WITH mysql_native_password BY 'app'
```
```
GRANT ALL PRIVILEGES ON *.* TO 'app'@'%' WITH GRANT OPTION;
```