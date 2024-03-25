package com.example.demo.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.Todo;

import reactor.core.publisher.Mono;
@Component
public class TodoClient {

	private static final Logger log = LoggerFactory.getLogger(TodoClient.class);
	private final WebClient webclient = WebClient.create();

	public List<Todo> getTodoList() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<Object> create(Todo request) {
		log.info("Request to service createPst :{}", request);
		return webclient.post().uri("https://jsonplaceholder.typicode.com/posts").accept(MediaType.APPLICATION_JSON)
				.bodyValue(request).exchangeToMono(response -> response.toEntity(Object.class))
				.map(entityString -> entityString.getBody())
				.doOnSuccess(responseFinal -> log.info("Response to service createPst :{}", responseFinal));

	}

	public Mono<Object> put(Todo request) {
		log.info("Request to service updatePost :{}", request);
		return webclient.put().uri("https://jsonplaceholder.typicode.com/posts/" + request.id()).accept(MediaType.APPLICATION_JSON)
				.bodyValue(request).exchangeToMono(response -> response.toEntity(Object.class))
				.map(entityString -> entityString.getBody())
				.doOnSuccess(responseFinal -> log.info("Response to service updatePost :{}", responseFinal));

	}
	
}
