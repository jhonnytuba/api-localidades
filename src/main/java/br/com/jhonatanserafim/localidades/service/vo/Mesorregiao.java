package br.com.jhonatanserafim.localidades.service.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mesorregiao {

    private Integer id;
    private String nome;

    @JsonProperty("UF")
    private Estado uf;

    public Integer getId() {
        return id;
    }

    public Mesorregiao setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Mesorregiao setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Estado getUf() {
        return uf;
    }

    public Mesorregiao setUf(Estado uf) {
        this.uf = uf;
        return this;
    }
}
