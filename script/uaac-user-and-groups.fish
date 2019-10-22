#!/usr/bin/env fish

echo "[ADD UAA USER]"
docker run --rm -v (pwd):/root -it shinyay/cf-uaac user add appuser -p appusersecret --emails appuser@pivotal.io

echo "[ADD UAA GROUP]"
docker run --rm -v (pwd):/root -it shinyay/cf-uaac group add resource.read
docker run --rm -v (pwd):/root -it shinyay/cf-uaac group add resource.write

echo "[ASIGN GROUP to USER]"
docker run --rm -v (pwd):/root -it shinyay/cf-uaac member add resource.read appuser
docker run --rm -v (pwd):/root -it shinyay/cf-uaac member add resource.write appuser
