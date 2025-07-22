Project to repduce issue https://github.com/quarkusio/quarkus/issues/49039

# To reproduce 

## run tests

```shell script
./mvnw package
```

## or launch quarkus application

```shell script
./mvnw quarkus:dev
```

### and call resources with X-Forwarded-* headers

```shell script
❯ curl -I \
  -H 'X-Forwarded-Port:81' \
  -H 'X-Forwarded-Proto:https' \
  -H 'X-Forwarded-Host:example.com' \
  -H 'X-Forwarded-Prefix:/some-forwarded-prefix' \
  http://localhost:8080/some-root-path/old-path/location
HTTP/1.1 302 Found
Location: https://example.com:81/some-root-path/new-path
```
Expected `https://example.com:81/some-forwarded-prefix/some-root-path/new-path`

```shell script
❯ curl -I \
  -H 'X-Forwarded-Port:81' \
  -H 'X-Forwarded-Proto:https' \
  -H 'X-Forwarded-Host:example.com' \
  -H 'X-Forwarded-Prefix:/some-forwarded-prefix' \
  http://localhost:8080/some-root-path/old-path/content-location
HTTP/1.1 302 Found
Content-Location: https://example.com:81/new-path
```

Expected `https://example.com:81/some-forwarded-prefix/new-path`
