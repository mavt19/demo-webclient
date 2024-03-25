package com.example.demo.controller;

import java.time.Duration;
import java.util.List;

import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Todo;
import com.example.demo.service.TodoService;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/demo")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

//    private final InventoryService inventoryService;
    private final TodoService todoService;

    @GetMapping
    public Flux<String> fluxOfStrings(){
    	log.info("fluxOfStrings");
    	return Flux.fromIterable(List.of("a", "b", "c", "d")).delayElements(Duration.ofSeconds(1))
    			.doOnSubscribe(x -> log.info("final1" + x))
    			.doFinally(x -> log.info("final2" + x));
    }

    @PostMapping
    public Mono<ResponseEntity<Object>> merge(@RequestBody Todo request){
    	log.info("create method principal request : {}", new Gson().toJson(request));
        return todoService.merge(request).map(resp -> ResponseEntity.ok().body(resp))
        		.doOnSuccess(responseFinal -> log.info("Response to method principal response :{}", new Gson().toJson(responseFinal.getBody())));
    }
//
//    @GetMapping("/{sku-code}")
//    public Mono<Boolean> isInStock(@NotBlank @PathVariable("sku-code") String skuCode){
//
//        return inventoryService.isInStock(skuCode);
//    }
}
