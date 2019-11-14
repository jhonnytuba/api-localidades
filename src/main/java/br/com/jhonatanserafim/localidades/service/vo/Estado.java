package br.com.jhonatanserafim.localidades.service.vo;

public class Estado {

    private Integer id;
    private String nome;
    private String sigla;
    private Regiao regiao;

    public Integer getId() {
        return id;
    }

    public Estado setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Estado setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getSigla() {
        return sigla;
    }

    public Estado setSigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public Estado setRegiao(Regiao regiao) {
        this.regiao = regiao;
        return this;
    }
}
