package com.example.travel.model;
public class Destino {
    private Long id;
    private String nome;
    private String localizacao;
    private String descricao;
    private Double preco;
    private Double notaMedia;
    private Integer quantidadeAvaliacoes;
    public Destino() {}
    public Destino(Long id, String nome, String localizacao, String descricao, Double preco) {
        this.id = id; this.nome = nome; this.localizacao = localizacao;
        this.descricao = descricao; this.preco = preco;
        this.notaMedia = 0.0; this.quantidadeAvaliacoes = 0;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getNome(){return nome;} public void setNome(String nome){this.nome=nome;}
    public String getLocalizacao(){return localizacao;} public void setLocalizacao(String l){this.localizacao=l;}
    public String getDescricao(){return descricao;} public void setDescricao(String d){this.descricao=d;}
    public Double getPreco(){return preco;} public void setPreco(Double p){this.preco=p;}
    public Double getNotaMedia(){return notaMedia;} public void setNotaMedia(Double n){this.notaMedia=n;}
    public Integer getQuantidadeAvaliacoes(){return quantidadeAvaliacoes;}
    public void setQuantidadeAvaliacoes(Integer q){this.quantidadeAvaliacoes=q;}
}