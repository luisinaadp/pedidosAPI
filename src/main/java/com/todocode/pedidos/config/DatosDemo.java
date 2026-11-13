package com.todocode.pedidos.config;

import com.todocode.pedidos.model.Pedido;
import com.todocode.pedidos.repository.PedidoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DatosDemo {
    @Bean
    CommandLineRunner cargarDatos(PedidoRepository repo) {
        return args -> {
            repo.save(new Pedido(101L, "luisina", "Teclado mecanico", new BigDecimal("25000.00")));
            repo.save(new Pedido(102L, "ibra", "Monitor 27 pulgadas", new BigDecimal("89000.00")));
            repo.save(new Pedido(103L, "luisina", "Mouse", new BigDecimal("12000.00")));
        };
    }
}
