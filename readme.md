# Postgres with Keycloak

## Network

Create a network between Keycloak and its db (PostgreSQL)

```
docker network create keycloak-postgres
```

## Postgres

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

## Keycloak
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
    --env KC_HOSTNAME=keycloak-instance \
    --detach \
    keycloak-poc \
    start-dev
```

Please create Keycloak's stuffs with below information
- Realm's name: app
- Realm's client: inventory-manager

# MySQL with App
## Network
Create a network between app and its db (MySQL)

```
docker network create app-mysql
```

## MySQL
### Build & Run MySQL
```
docker pull mysql/mysql-server:8.0
```

```
docker run \
    --rm \
    --network app-mysql \
    --name appdb \
    --publish 3306:3306 \
    --env MYSQL_ROOT_PASSWORD=admin \
    --volume mysql:/var/lib/mysql \
    --detach \
    mysql/mysql-server:8.0
```

### Create an account for app and grant permissions
```
CREATE USER 'app'@'%' IDENTIFIED WITH mysql_native_password BY 'app';
```
```
GRANT ALL PRIVILEGES ON *.* TO 'app'@'%' WITH GRANT OPTION;
```

## App
```
docker pull openjdk:22-slim-bullseye
```
```
docker build ./App/InventoryManager -t app
```
```
docker run \
    --rm \
    --name app \
    --network app-mysql \
    --publish 8088:8088 \
    --env SPRING_DATASOURCE_URL=jdbc:mysql://appdb:3306/mysql \
    --env SPRING_DATASOURCE_USERNAME=app \
    --env SPRING_DATASOURCE_PASSWORD=app \
    --env KEYCLOAK_ISSUER_URI=http://keycloak-instance:8080/realms/app \
    --env KEYCLOAK_APP_CLIENT_ID=inventory-manager \
    app 
```

# App and Keycloak
Create a network between Keycloak and our App (PostgreSQL)
```
docker network create keycloak-app
```

Connect app with keycloak
```
docker network connect keycloak-app keycloak-instance
docker network connect keycloak-app app
```

# Note.

## Warning

### While config authorization service with scope and resource

While config with Keycloak Authorization service, please check the resource url carefully.
[![Authz Resources config warning](/Resources/imgs/Keycloak-Authz-Resources-config.png)]

### After editing authorization service config
The change would not apply immediately, please waiting about 1-2 minute(s) then regression testing

