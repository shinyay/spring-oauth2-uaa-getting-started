# Spring OAuth 2.0 Client and UAA

Overview

## Description

## Demo
### Required Configuration
#### Spring Profile for DataSource
```
$ set -x SPRING_PROFILES "default,hsqldb"
```

#### JWS Key Pair
```
$ openssl genrsa -out signingkey.pem 2048
$ openssl rsa -in signingkey.pem -pubout -out verificationkey.pem

$ set -x JWT_TOKEN_SIGNING_KEY (cat signingkey.pem)
$ set -x JWT_TOKEN_VERIFICATION_KEY (cat verificationkey.pem)
```

### Stating up UAA
```
$ cd <UAA_DIR>
$ ./gradlew run
```

### Populating Clients and Users Using UAAC
```
$ uaac target http://<UAA_ADDRESS>:8080/uaa
```

```
$ docker run --rm -v (pwd):/root -it shinyay/cf-uaac target http://<UAA_ADDRESS>:8080/uaa
```

- <UAA_ADDRESS>: `ifconfig en0 | awk '/inet / {print $2}'`


## Features

- feature:1
- feature:2

## Requirement

## Usage

## Installation

## Licence

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/34c6fdd50d54aa8e23560c296424aeb61599aa71/LICENSE)

## Author

[shinyay](https://github.com/shinyay)
