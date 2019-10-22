#!/usr/bin/env fish

docker run --rm -v (pwd):/root -it shinyay/cf-uaac token client get admin -s adminsecret
