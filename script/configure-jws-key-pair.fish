#!/usr/bin/env fish

if test -e signingkey.pem
  openssl genrsa -out signingkey.pem 2048
end
if test -e verificationkey.pem
  openssl rsa -in signingkey.pem -pubout -out verificationkey.pem
end

set -x JWT_TOKEN_SIGNING_KEY (cat signingkey.pem)
set -x JWT_TOKEN_VERIFICATION_KEY (cat verificationkey.pem)
