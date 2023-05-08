package com.example.hinovaoficinas.data.models.indication;

public class Indicacao {
    private String CodigoAssociacao;
    private String DataCriacao;
    private String CpfAssociado;
    private String EmailAssociado;
    private String NomeAssociado;
    private String TelefoneAssociado;
    private String PlacaVeiculoAssociado;
    private String NomeAmigo;
    private String TelefoneAmigo;
    private String EmailAmigo;

    public Indicacao(String codigoAssociacao, String dataCriacao, String cpfAssociado, String emailAssociado, String nomeAssociado, String telefoneAssociado, String placaVeiculoAssociado, String nomeAmigo, String telefoneAmigo, String emailAmigo) {
        CodigoAssociacao = codigoAssociacao;
        DataCriacao = dataCriacao;
        CpfAssociado = cpfAssociado;
        EmailAssociado = emailAssociado;
        NomeAssociado = nomeAssociado;
        TelefoneAssociado = telefoneAssociado;
        PlacaVeiculoAssociado = placaVeiculoAssociado;
        NomeAmigo = nomeAmigo;
        TelefoneAmigo = telefoneAmigo;
        EmailAmigo = emailAmigo;
    }
}

