package io.pivotal.shinyay.uaa.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
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

        return "Hello, ${authenticationToken.principal.name}" +
                "</br></br>" +
                "Here is your access token :</br>" +
                "${oAuth2AccessToken.tokenValue}</br>" +
                "</br>You can use it to call these Resource Server APIs:</br></br>" +
                "<a href='/read'>Call Resource Server Read API</a>" +
                "</br>" +
                "<a href='/write'>Call Resource Server Write API</a>"
    }

    fun retrieveAccessToken(authenticationToken: OAuth2AuthenticationToken, url: String): String {
        val oAuth2AuthorizedClient: OAuth2AuthorizedClient =
                this.authorizedClientService
                        .loadAuthorizedClient(
                                authenticationToken.authorizedClientRegistrationId,
                                authenticationToken.name
                        )
        val oAuth2AccessToken = oAuth2AuthorizedClient.accessToken

        val httpHeaders = HttpHeaders()
        httpHeaders.add("Authorization", "Bearer " + oAuth2AccessToken.tokenType)

        val httpEntity: HttpEntity<String> = HttpEntity("parameters", httpHeaders)
        val responseEntity: ResponseEntity<String>

        var response: String

        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String::class.java)
            response = responseEntity.body!!
        } catch (e: HttpClientErrorException) {
            response = e.message!!
        }
        return response
    }
}

