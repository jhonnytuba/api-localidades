package br.com.jhonatanserafim.localidades.api.v1.converter;

import br.com.jhonatanserafim.localidades.api.v1.dto.LocalidadeResponse;
import br.com.jhonatanserafim.localidades.service.vo.Municipio;

import java.util.List;
import java.util.stream.Collectors;

public final class LocalidadeJsonConverter {

    private LocalidadeJsonConverter() {
    }

    public static List<LocalidadeResponse> convert(List<Municipio> municipios) {
        return municipios.stream()
                .map(LocalidadeJsonConverter::convert)
                .collect(Collectors.toList());
    }

    private static LocalidadeResponse convert(Municipio municipio) {
        return new LocalidadeResponse()
                .setIdEstado(municipio.getIdEstado())
                .setSiglaEstado(municipio.getSiglaEstado())
                .setRegiaoNome(municipio.getRegiaoNome())
                .setNomeCidade(municipio.getNome())
                .setNomeMesorregiao(municipio.getNomeMesorregiao())
                .setNomeFormatado(municipio.getNomeFormatado());
    }
}
