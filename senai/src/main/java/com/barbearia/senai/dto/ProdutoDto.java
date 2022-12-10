package com.barbearia.senai.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProdutoDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String servico;
    @NotBlank
    private String data;
    @NotBlank
    private String hora;


    public ProdutoDto() {
    }

    public ProdutoDto(String nome, String servico, String data, String hora) {
        this.nome = nome;
        this.servico = servico;
        this.data = data;
        this.hora = hora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
