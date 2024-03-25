package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.client.TodoClient;
import com.example.demo.dto.Todo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TodoService {
	private final TodoClient todoClient;
	public List<Todo> getTodoList() {
		return todoClient.getTodoList();
	}
	private Mono<Object> create(Todo request) {
		return todoClient.create(request);
	}
	private Mono<Object> update(Todo request) {
		return todoClient.put(request);
	}
	
	public Mono<Object> merge(Todo request) {
		return Mono.zip(create(request), update(request))
		.map(tuple -> tuple.getT1());
	}
}
