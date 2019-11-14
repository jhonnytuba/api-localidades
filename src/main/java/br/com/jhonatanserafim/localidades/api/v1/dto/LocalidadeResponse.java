package br.com.jhonatanserafim.localidades.api.v1.dto;

public class LocalidadeResponse {

    private Integer idEstado;
    private String siglaEstado;
    private String regiaoNome;
    private String nomeCidade;
    private String nomeMesorregiao;
    private String nomeFormatado;

    public Integer getIdEstado() {
        return idEstado;
    }

    public LocalidadeResponse setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public LocalidadeResponse setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
        return this;
    }

    public String getRegiaoNome() {
        return regiaoNome;
    }

    public LocalidadeResponse setRegiaoNome(String regiaoNome) {
        this.regiaoNome = regiaoNome;
        return this;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public LocalidadeResponse setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
        return this;
    }

    public String getNomeMesorregiao() {
        return nomeMesorregiao;
    }

    public LocalidadeResponse setNomeMesorregiao(String nomeMesorregiao) {
        this.nomeMesorregiao = nomeMesorregiao;
        return this;
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }

    public LocalidadeResponse setNomeFormatado(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
        return this;
    }
}
