package br.com.ifsp.aluno.allex.simuladorfinanciamento.business;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.Constants;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.model.Financiamento;

public abstract class CalculadoraFinanciamento {

    protected String mensagemErro;

    protected Double pctEntradaMinima;

    protected CalculadoraFinanciamento(Double pctEntradaMinima) {
        this.pctEntradaMinima = pctEntradaMinima;
    }

    public abstract boolean calculaFinanciamento(Financiamento financiamento);

    public abstract Double determinaTaxaJuros(Double renda);

    public Double calculaEntradaMinima(Double valor) {
        return valor * pctEntradaMinima;
    }

    protected boolean isValorParcelaPermitido(Double valorParcela, Double renda) {
        return valorParcela <= (renda * .3);
    }

    protected double ajustaValorJuros(double valor, double taxaJuros, int qtdParcelas) {
        Double valorFuturo = valor * Math.pow((1 + taxaJuros), qtdParcelas);
        Double valorParcelas = (valorFuturo * taxaJuros) / (Math.pow((1 + taxaJuros), qtdParcelas) - 1);
        return valorParcelas * qtdParcelas;
    }

    protected Double aplicaTaxasAdicionaisValorNovo(Double valor) {
        return valor;
    }

    protected Double aplicaTaxasAdicionaisValorUsado(Double valor) {
        return valor;
    }

    public String getUltimoErro(){
        return mensagemErro;
    }

}
