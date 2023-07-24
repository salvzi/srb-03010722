package com.atguigu.srb.gateway.filter;


import com.atguigu.srb.common.util.JwtUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Component
public class AuthFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        URI uri = request.getURI();
        RequestPath path = request.getPath();
        System.out.println(uri);
        System.out.println("全局过滤器");
        
        // 获得token
        List<String> tokens = request.getHeaders().get("token");
        if(null!=tokens&&tokens.size()>0){
            String token = tokens.get(0);
            System.out.println(token);
            System.out.println("验证token");
            boolean b = JwtUtils.checkToken(token);
            if(b){
                Long userId = JwtUtils.getUserId(token);
                // 通过request，将userId传递给微服务
                request.mutate().header("userId",userId+"");
                exchange.mutate().request(request);
            }
        }
        return chain.filter(exchange);
    }
}
