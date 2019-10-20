#!/usr/bin/env fish

openssl genrsa -out signingkey.pem 2048
openssl rsa -in signingkey.pem -pubout -out verificationkey.pem

set -x JWT_TOKEN_SIGNING_KEY (cat signingkey.pem)
set -x JWT_TOKEN_VERIFICATION_KEY (cat verificationkey.pem)
