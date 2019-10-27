package io.pivotal.shinyay.uaa.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class OAuth2ClientController(@Value("\${resource.server.url}") val remoteResourceServer: String,
                             val authorizedClientService: OAuth2AuthorizedClientService) {
    val restTemplate = RestTemplate()

    @RequestMapping("/")
    fun index(authenticationToken: OAuth2AuthenticationToken): String {
        val oAuth2AuthorizedClient: OAuth2AuthorizedClient =
                this.authorizedClientService
                        .loadAuthorizedClient<OAuth2AuthorizedClient>(
                                authenticationToken.authorizedClientRegistrationId,
                                authenticationToken.name)
        val oAuth2AccessToken = oAuth2AuthorizedClient.accessToken

        val message = "Hello, ${authenticationToken.principal.name}" +
                "</br></br>" +
                "Here is your access token :</br>" +
                "${oAuth2AccessToken.tokenValue}</br>" +
                "</br>You can use it to call these Resource Server APIs:</br></br>" +
                "<a href='/read'>Call Resource Server Read API</a>" +
                "</br>" +
                "<a href='/write'>Call Resource Server Write API</a>"
        return message
    }
}

