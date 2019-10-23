#!/usr/bin/env fish

echo "[TARGET UAA SERVER LOCATION]"
docker run --rm -v (pwd):/root -it shinyay/cf-uaac target http://(ifconfig en0 | awk '/inet / {print $2}'):8080/uaa
