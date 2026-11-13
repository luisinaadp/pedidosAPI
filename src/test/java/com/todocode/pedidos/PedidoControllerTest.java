package com.todocode.pedidos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class PedidoControllerTest {
    @Autowired MockMvc mvc;
    @Test
    void listarSoloMisPedidos() throws Exception {
        mvc.perform(get("/api/pedidos").with(httpBasic("luisina", "demo123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].propietario").value("luisina"))
                .andExpect(jsonPath("$[1].propietario").value("luisina"));
    }
    @Test
    void obtenerMiPedidoFunciona() throws Exception {
        mvc.perform(get("/api/pedidos/101").with(httpBasic("luisina", "demo123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.propietario").value("luisina"));
    }
    @Test
    void noPuedoVerPedidosDeIbra() throws Exception {
        mvc.perform(get("/api/pedidos/102").with(httpBasic("luisina", "demo123"))).andExpect(status().isForbidden());
    }
    @Test
    void obtenerPedidoDeIbraFunciona() throws Exception {
        mvc.perform(get("/api/pedidos/102").with(httpBasic("ibra", "demo123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.propietario").value("ibra"));
    }
    @Test
    void noPuedoVerPedidosDeLuisina() throws Exception {
        mvc.perform(get("/api/pedidos/101").with(httpBasic("ibra", "demo123"))).andExpect(status().isForbidden());
    }
    @Test
    void pedidoInexistenteDevuelveNotFound() throws Exception {
        mvc.perform(get("/api/pedidos/999").with(httpBasic("luisina", "demo123"))).andExpect(status().isNotFound());
    }
    @Test
    void obtenerPedidoRequiereAutenticacion() throws Exception {
        mvc.perform(get("/api/pedidos/101")).andExpect(status().isUnauthorized());
    }
}
