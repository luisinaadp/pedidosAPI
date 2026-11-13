package com.todocode.pedidos.service;

import com.todocode.pedidos.model.Pedido;
import com.todocode.pedidos.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<Pedido> listarMisPedidos(String usuarioAutenticado) {
        return repository.findByPropietario(usuarioAutenticado);
    }

    public Pedido obtenerPedido(Long id, String usuarioAutenticado) {
        Pedido pedido = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));
        if (!usuarioAutenticado.equals(pedido.getPropietario())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado");
        }
        return pedido;
    }
}
