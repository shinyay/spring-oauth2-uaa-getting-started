#!/usr/bin/env fish

echo "[RETRIEVE TOKEN FROM UAA SERVER]"
docker run --rm -v (pwd):/root -it shinyay/cf-uaac token client get admin -s adminsecret
