package br.com.ifsp.aluno.allex.simuladorfinanciamento.business;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.model.Financiamento;

public class CalculadoraImovel {

    private String mensagemErro;

    public boolean calculaFinanciamento(Financiamento financiamento) {

        if(financiamento.getValor() <= 0) {
            mensagemErro = "Informe o valor do imóvel";
            return false;
        }
        else if(financiamento.getEntrada() <= 0) {
            mensagemErro = "Informe o valor da entrada";
            return false;
        }
        else if(financiamento.getEntrada() < calculaEntradaMinima(financiamento.getValor())) {
            mensagemErro = "O valor de entrada deve ser pelo menos 20% do valor do imóvel.";
            return false;
        }
        else if(financiamento.getQtdParcelas() <= 0) {
            mensagemErro = "Informe a quantidade de parcelas";
            return false;
        }
        else if(financiamento.getRendaMensal() <= 0) {
            mensagemErro = "Informe sua renda líquida mensal";
            return false;
        }

        financiamento.setValorFinal(financiamento.getValor() - financiamento.getEntrada());

        if(financiamento.isNovo())
            financiamento.setValorFinal(ajustaValorNovo(financiamento.getValorFinal()));
        else
            financiamento.setValorFinal(ajustaValorUsado(financiamento.getValorFinal()));

        double taxaJuros = determinaTaxaJuros(financiamento.getRendaMensal());

        financiamento.setValorFinal(ajustaValorJuros(financiamento.getValorFinal(), taxaJuros, financiamento.getQtdParcelas()));

        double valorParcela = financiamento.getValorFinal() / financiamento.getQtdParcelas();

        if(!isValorParcelaPermitido(valorParcela, financiamento.getRendaMensal())){
            mensagemErro = "Desculpe, mas parece que você não possui os requisitos para o financiamento.";
            return false;
        }

        financiamento.setValorParcelas(valorParcela);

        return true;
    }

    private double ajustaValorJuros(double valor, double taxaJuros, int qtdParcelas) {
        return ((valor / qtdParcelas) + (valor * taxaJuros)) * qtdParcelas;
    }

    public Double ajustaValorNovo(Double valor) {
        return valor + calculaHabitese(valor);
    }

    public Double ajustaValorUsado(Double valor) {
        return valor + calculaTransferencia(valor);
    }

    public Double calculaEntradaMinima(Double valor) {
        return valor * .2;
    }

    private Double calculaTransferencia(Double valor) {
        return valor * .02;
    }

    private Double calculaHabitese(Double valor) {
        return valor * .05;
    }

    private Double determinaTaxaJuros(Double renda) {
        if(renda <= 3500)
            return .03;
        else if(renda <= 5000)
            return .025;
        else
            return .02;
    }

    private boolean isValorParcelaPermitido(Double valorParcela, Double renda) {
        return valorParcela <= (renda * .3);
    }

    public String getUltimoErro(){
        return mensagemErro;
    }

}
