package br.com.jhonatanserafim.localidades.service;

import br.com.jhonatanserafim.localidades.service.vo.Estado;
import br.com.jhonatanserafim.localidades.service.vo.Municipio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "localidadeFeignClient", url = "${servicodados.ibge.url}", fallback = LocalidadeFallback.class)
public interface LocalidadeFeignClient {

    @GetMapping(value = "/api/v1/localidades/estados")
    List<Estado> listaTodosEstados();

    @GetMapping(value = "/api/v1/localidades/estados/{idEstado}/municipios")
    List<Municipio> listaTodosMunicipios(@PathVariable("idEstado") Integer idEstado);
}
