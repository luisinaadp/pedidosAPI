package com.todocode.pedidos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class PedidoControllerTest {
    @Autowired MockMvc mvc;
    @Test @WithMockUser(username="luisina")
    void listarSoloMisPedidos() throws Exception {
        mvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].propietario").value("luisina"))
                .andExpect(jsonPath("$[1].propietario").value("luisina"));
    }
    @Test @WithMockUser(username="luisina")
    void obtenerMiPedidoFunciona() throws Exception {
        mvc.perform(get("/api/pedidos/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.propietario").value("luisina"));
    }
    // Test de regresion de seguridad: habilitar despues de corregir la vulnerabilidad.
    // @Test @WithMockUser(username="luisina")
    // void noPuedoVerPedidosDeIbra() throws Exception {
    //     mvc.perform(get("/api/pedidos/102")).andExpect(status().isForbidden());
    // }
}
