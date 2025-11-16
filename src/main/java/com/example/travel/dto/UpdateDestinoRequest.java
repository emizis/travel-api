package com.example.travel.dto;
public class UpdateDestinoRequest {
    private String nome; private String localizacao; private String descricao; private Double preco;
    public UpdateDestinoRequest() {}
    public String getNome(){return nome;} public void setNome(String n){this.nome=n;}
    public String getLocalizacao(){return localizacao;} public void setLocalizacao(String l){this.localizacao=l;}
    public String getDescricao(){return descricao;} public void setDescricao(String d){this.descricao=d;}
    public Double getPreco(){return preco;} public void setPreco(Double p){this.preco=p;}
}