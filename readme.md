# Network

Create a network between Keycloak and its db (PostgreSQL)

```
docker network create keycloak-postgres
```

# Postgres

## Pull image
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