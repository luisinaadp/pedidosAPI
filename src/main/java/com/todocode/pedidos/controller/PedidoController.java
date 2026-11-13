package com.todocode.pedidos.controller;

import com.todocode.pedidos.model.Pedido;
import com.todocode.pedidos.service.PedidoService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pedido> misPedidos(Authentication authentication) {
        return service.listarMisPedidos(authentication.getName());
    }

    @GetMapping("/{id}")
    public Pedido obtenerPedido(@PathVariable Long id, Authentication authentication) {
        return service.obtenerPedido(id, authentication.getName());
    }
}
