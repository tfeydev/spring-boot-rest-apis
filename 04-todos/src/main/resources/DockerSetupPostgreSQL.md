# Docker Setup for local PostgreSQL

## Check the docker version

docker --version

## Run a PostgreSQL container in detached mode with:

- postgres user password set to 'secret'
- a database named 'tododb' created
- container named 'postgresdb'
- port 5432 in the container mapped to port 5433 on the host

``` bash
docker run -d \
  --name postgresdb \
  -e POSTGRES_USER=root \
  -e POSTGRES_PASSWORD=secret \
  -e POSTGRES_DB=tododb \
  -p 5433:5432 \
  postgres:18
```

## Setup in Spring

### application.properties

``` bash
# Datasource
spring.datasource.url=jdbc:postgresql://localhost:5433/tododb
spring.datasource.username=root
spring.datasource.password=secret
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Commands to solve problems

``` bash
docker stop postgresdb

docker start postgresdb

docker restart postgresdb

docker rm postgresdb
```
