package com.smart_ski_rent_ver1_2;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Component;



@Component
public class FilterChainLogger {

    @Autowired
    private FilterChainProxy filterChainProxy;

    @PostConstruct
    public void logFilterChains() {
        filterChainProxy.getFilterChains().forEach(chain -> {
            System.out.println("Filter chain: " + chain.getFilters());
        });
    }
}
