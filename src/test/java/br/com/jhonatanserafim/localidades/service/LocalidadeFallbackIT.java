package br.com.jhonatanserafim.localidades.service;

import br.com.jhonatanserafim.localidades.BaseIT;
import br.com.jhonatanserafim.localidades.service.vo.Estado;
import br.com.jhonatanserafim.localidades.service.vo.Municipio;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalidadeFallbackIT extends BaseIT {

    @Autowired
    private LocalidadeFeignClient localidadeFeignClient;

    @Test
    public void deveRetornarListaEstadosVazia() {
        List<Estado> estados = localidadeFeignClient.listaTodosEstados();
        assertThat(estados).isEmpty();
    }

    @Test
    public void deveRetornarListaMunicipiosVazia() {
        List<Municipio> municipios = localidadeFeignClient.listaTodosMunicipios(32);
        assertThat(municipios).isEmpty();
    }
}
