package br.com.jhonatanserafim.localidades.api.v1;

import br.com.jhonatanserafim.localidades.api.v1.converter.LocalidadeCsvConverter;
import br.com.jhonatanserafim.localidades.api.v1.converter.LocalidadeJsonConverter;
import br.com.jhonatanserafim.localidades.api.v1.dto.LocalidadeResponse;
import br.com.jhonatanserafim.localidades.api.v1.exception.NotFoundException;
import br.com.jhonatanserafim.localidades.service.LocalidadeService;
import br.com.jhonatanserafim.localidades.service.vo.Municipio;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/localidades")
public class LocalidadeRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalidadeRest.class);

    private final LocalidadeService localidadeService;

    public LocalidadeRest(LocalidadeService localidadeService) {
        this.localidadeService = localidadeService;
    }

    @ApiOperation("Exporta as localidades")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LocalidadeResponse> exportaLocalidadesEmJson() {
        LOGGER.debug("Executing exportaLocalidadesEmJson");

        List<Municipio> municipios = localidadeService.listaTodosMunicipios();
        return LocalidadeJsonConverter.convert(municipios);
    }

    @ApiOperation("Exporta as localidades em csv")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public String exportaLocalidadesEmCsv(HttpServletResponse response) {
        LOGGER.debug("Executing exportaLocalidadesEmCsv");

        response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + UUID.randomUUID().toString() + ".csv\"");

        List<Municipio> municipios = localidadeService.listaTodosMunicipios();
        return LocalidadeCsvConverter.convert(municipios);
    }

    @ApiOperation("Retorna o ID da cidade")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{nomeCidade}")
    public Integer pegaLocalidade(@PathVariable("nomeCidade") String nomeCidade) {
        LOGGER.debug("Executing pegaLocalidade");

        Optional<Municipio> municipio = localidadeService.pegaMunicipio(nomeCidade);
        return municipio
                .map(Municipio::getId)
                .orElseThrow(NotFoundException::new);
    }
}
