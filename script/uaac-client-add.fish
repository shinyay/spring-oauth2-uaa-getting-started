#!/usr/bin/env fish

docker run --rm -v (pwd):/root -it shinyay/cf-uaac client add webappclient -s webappclientsecret \
                                              --name WebAppClient \
                                              --scope resource.read,resource.write,openid,profile,email,address,phone \
                                              --authorized_grant_types authorization_code,refresh_token,client_credentials,password \
                                              --authorities uaa.resource \
                                              --redirect_uri http://localhost:8081/login/oauth2/code/uaa
