package br.com.ifsp.aluno.allex.simuladorfinanciamento.business;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.model.Financiamento;

public class CalculadoraAutomovel {

    private String mensagemErro;

    public boolean calculaFinanciamento(Financiamento financiamento) {

        if(financiamento.getValor() <= 0) {
            mensagemErro = "Informe o valor do automóvel";
            return false;
        }
        else if(financiamento.getEntrada() <= 0) {
            mensagemErro = "Informe o valor da entrada";
            return false;
        }
        else if(financiamento.getEntrada() < calculaEntradaMinima(financiamento.getValor())) {
            mensagemErro = "O valor de entrada deve ser pelo menos 5% do valor do automóvel.";
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
        return valor + calculaTaxaEmplacamento(valor) + calculaIPVA(valor);
    }

    public Double calculaEntradaMinima(Double valor) {
        return valor * .05;
    }

    private Double calculaTaxaEmplacamento(Double valor) {
        return valor * .01;
    }

    private Double calculaIPVA(Double valor) {
        return valor * .04;
    }

    private Double determinaTaxaJuros(Double renda) {
        if(renda <= 3500)
            return .06;
        else if(renda <= 5000)
            return .05;
        else
            return .04;
    }

    private boolean isValorParcelaPermitido(Double valorParcela, Double renda) {
        return valorParcela <= (renda * .3);
    }

    public String getUltimoErro(){
        return mensagemErro;
    }

}
