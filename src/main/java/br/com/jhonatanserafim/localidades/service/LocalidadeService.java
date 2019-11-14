package br.com.jhonatanserafim.localidades.service;

import br.com.jhonatanserafim.localidades.service.vo.Estado;
import br.com.jhonatanserafim.localidades.service.vo.Municipio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocalidadeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalidadeService.class);

    @Autowired(required = false)
    private LocalidadeService self;

    @Autowired
    private LocalidadeFeignClient localidadeFeignClient;

    public List<Municipio> listaTodosMunicipios() {
        LOGGER.info("Buscando os municípios");
        return self.listaTodosEstados().stream()
                .flatMap(estado -> self.listaTodosMunicipiosPorEstado(estado).stream())
                .collect(Collectors.toList());
    }

    @Cacheable(value = "pegarMunicipio", key = "#nomeCidade", unless = "#result == null")
    public Optional<Municipio> pegaMunicipio(String nomeCidade) {
        LOGGER.info("Buscando o município: {}", nomeCidade);
        return self.listaTodosMunicipios().stream().parallel()
                .filter(municipio -> municipio.getNome().equalsIgnoreCase(nomeCidade))
                .findAny();
    }

    @Cacheable(value = "listaTodosMunicipiosPorEstado", key = "#estado.id", unless = "#result.isEmpty()")
    public List<Municipio> listaTodosMunicipiosPorEstado(Estado estado) {
        LOGGER.info("Buscando os municípios externamente com o estado: {}", estado.getId());
        return localidadeFeignClient.listaTodosMunicipios(estado.getId());
    }

    @Cacheable(value = "listaTodosEstados", unless = "#result.isEmpty()")
    public List<Estado> listaTodosEstados() {
        LOGGER.info("Buscando os estados externamente");
        return localidadeFeignClient.listaTodosEstados();
    }
}
