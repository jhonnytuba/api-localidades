package br.com.jhonatanserafim.localidades.api.v1.converter;

import br.com.jhonatanserafim.localidades.service.vo.Municipio;

import java.util.List;

public final class LocalidadeCsvConverter {

    private static final char SEPARADOR_CSV = ';';
    private static final String PULA_LINHA_CSV = System.getProperty("line.separator");

    private LocalidadeCsvConverter() {
    }

    public static String convert(List<Municipio> municipios) {
        StringBuilder sb = new StringBuilder();

        sb.append("idEstado").append(SEPARADOR_CSV)
                .append("siglaEstado").append(SEPARADOR_CSV)
                .append("regiaoNome").append(SEPARADOR_CSV)
                .append("nomeCidade").append(SEPARADOR_CSV)
                .append("nomeMesorregiao").append(SEPARADOR_CSV)
                .append("nomeFormatado").append(PULA_LINHA_CSV);

        municipios.forEach(municipio ->
                sb.append(municipio.getIdEstado()).append(SEPARADOR_CSV)
                        .append(municipio.getSiglaEstado()).append(SEPARADOR_CSV)
                        .append(municipio.getRegiaoNome()).append(SEPARADOR_CSV)
                        .append(municipio.getNome()).append(SEPARADOR_CSV)
                        .append(municipio.getNomeMesorregiao()).append(SEPARADOR_CSV)
                        .append(municipio.getNomeFormatado()).append(PULA_LINHA_CSV)
        );

        return sb.toString();
    }
}
