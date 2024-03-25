//package com.example.demo.filter;
//
//import java.util.UUID;
//
//import org.slf4j.MDC;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//
//import io.micrometer.context.ContextRegistry;
//import reactor.core.publisher.Mono;
//import reactor.util.context.Context;
//
//@Component
//@Order(1)
//public class MdcFilter implements WebFilter {
//
//
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//		
//		ContextRegistry.getInstance()
//		  .registerThreadLocalAccessor("traceId",
//		    () -> MDC.get("traceId"),
//		    traceId -> MDC.put("traceId", traceId),
//		    () -> MDC.remove("traceId"));
//		
//        return chain.filter(exchange).contextWrite(Context.of("traceId", UUID.randomUUID().toString()));
//	}
//
//}
