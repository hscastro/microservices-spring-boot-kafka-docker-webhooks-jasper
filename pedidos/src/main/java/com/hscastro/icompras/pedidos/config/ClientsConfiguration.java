package com.hscastro.icompras.pedidos.config;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.hscastro.icompras.pedidos.client")
public class ClientsConfiguration {
}
