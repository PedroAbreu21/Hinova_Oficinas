package com.example.hinovaoficinas.data.models.indication;

public class IndicacaoEnvio {
    private Indicacao Indicacao;
    private String Remetente;
    private String[] Copias;

    public IndicacaoEnvio(Indicacao indicacao, String remetente, String[] copias) {
        Indicacao = indicacao;
        Remetente = remetente;
        Copias = copias;
    }
}
