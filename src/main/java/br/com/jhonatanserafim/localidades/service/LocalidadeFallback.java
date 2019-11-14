package br.com.jhonatanserafim.localidades.service;

import br.com.jhonatanserafim.localidades.service.vo.Estado;
import br.com.jhonatanserafim.localidades.service.vo.Municipio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LocalidadeFallback implements LocalidadeFeignClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalidadeFallback.class);

    @Override
    public List<Estado> listaTodosEstados() {
        LOGGER.warn("Buscando os estados do fallback");
        return Collections.emptyList();
    }

    @Override
    public List<Municipio> listaTodosMunicipios(Integer idEstado) {
        LOGGER.warn("Buscando os municípios do fallback com estado: {}", idEstado);
        return Collections.emptyList();
    }
}
