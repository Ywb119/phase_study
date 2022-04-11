package com.study.predicate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
@Component
public class TokenRoutePredicateFactory extends AbstractRoutePredicateFactory<TokenRoutePredicateFactory.Config> {

    public TokenRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(TokenRoutePredicateFactory.Config config) {
        return exchange -> {
            String headerName = config.getHeaderName();
            HttpHeaders headers = exchange.getRequest().getHeaders();
            List<String> header = headers.get(headerName);
            log.info("Token Predicate headers:{}", header);
            return header != null && header.size() > 0;
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        //顺序加载方式
        return Arrays.asList("headerName", "headerValue");
    }

    @Override
    public ShortcutType shortcutType() {
        return ShortcutType.DEFAULT;
    }


    @Data
    public static class Config {
        private String headerName;
        private String headerValue;
    }

}
