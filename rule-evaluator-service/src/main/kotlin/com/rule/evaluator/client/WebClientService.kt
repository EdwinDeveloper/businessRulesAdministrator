package com.rule.evaluator.client

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.util.retry.Retry
import java.time.Duration

@Service
class WebClientService(private val webClient: WebClient.Builder) {

    private val client: WebClient = webClient
        .baseUrl("http://localhost:8080/V1/Group/")
        .defaultHeader("Content-Type", "application/json")
        .build()
    fun <T> getRequest(endpoint: String, queryParams: Map<String, String> = emptyMap(), clazz: Class<T>): Mono<T> {
        return client.get()
            .uri { uriBuilder ->
                val uri = uriBuilder.path(endpoint)
                queryParams.forEach { (key, value) -> uri.queryParam(key, value) }
                uri.build()
            }
            .retrieve()
            .onStatus({ status -> status.is4xxClientError }) { response ->
                Mono.error(RuntimeException("Client error occurred: ${response.statusCode()}"))
            }
            .onStatus({ status -> status.is5xxServerError }) { response ->
                Mono.error(RuntimeException("Server error occurred: ${response.statusCode()}"))
            }
            .bodyToMono(clazz)
            .doOnError { e -> println("Error occurred during GET request: ${e.message}") }
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))  // Retry 3 times with 2 seconds interval
    }
    fun <T> postRequest(endpoint: String, body: T): Mono<String> {
        return client.post()
            .uri(endpoint)
            .bodyValue(body as Any)  // Explicit cast to Any
            .retrieve()
            .onStatus({ status -> status.is4xxClientError }) { response ->
                Mono.error(RuntimeException("Client error occurred: ${response.statusCode()}"))
            }
            .onStatus({ status -> status.is5xxServerError }) { response ->
                Mono.error(RuntimeException("Server error occurred: ${response.statusCode()}"))
            }
            .bodyToMono(String::class.java)
            .doOnError { e -> println("Error occurred during POST request: ${e.message}") }
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
    }

}
