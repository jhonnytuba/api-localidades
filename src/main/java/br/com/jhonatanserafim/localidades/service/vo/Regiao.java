package br.com.jhonatanserafim.localidades.service.vo;

public class Regiao {

    private Integer id;
    private String nome;
    private String sigla;

    public Integer getId() {
        return id;
    }

    public Regiao setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Regiao setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getSigla() {
        return sigla;
    }

    public Regiao setSigla(String sigla) {
        this.sigla = sigla;
        return this;
    }
}
