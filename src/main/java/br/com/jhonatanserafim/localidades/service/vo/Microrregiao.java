package br.com.jhonatanserafim.localidades.service.vo;

public class Microrregiao {

    private Integer id;
    private String nome;
    private Mesorregiao mesorregiao;

    public Integer getId() {
        return id;
    }

    public Microrregiao setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Microrregiao setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Mesorregiao getMesorregiao() {
        return mesorregiao;
    }

    public Microrregiao setMesorregiao(Mesorregiao mesorregiao) {
        this.mesorregiao = mesorregiao;
        return this;
    }
}
