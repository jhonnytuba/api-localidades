package br.com.jhonatanserafim.localidades.service.vo;

public class Municipio {

    private Integer id;
    private String nome;
    private Microrregiao microrregiao;

    public Integer getIdEstado() {
        return getEstado().getId();
    }

    public String getSiglaEstado() {
        return getEstado().getSigla();
    }

    public String getRegiaoNome() {
        return getEstado().getRegiao().getNome();
    }

    public String getNomeMesorregiao() {
        return getMesorregiao().getNome();
    }

    public String getNomeFormatado() {
        return getNome() + '/' + getSiglaEstado();
    }

    private Mesorregiao getMesorregiao() {
        return getMicrorregiao().getMesorregiao();
    }

    private Estado getEstado() {
        return getMesorregiao().getUf();
    }

    public Integer getId() {
        return id;
    }

    public Municipio setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Municipio setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Microrregiao getMicrorregiao() {
        return microrregiao;
    }

    public Municipio setMicrorregiao(Microrregiao microrregiao) {
        this.microrregiao = microrregiao;
        return this;
    }
}
