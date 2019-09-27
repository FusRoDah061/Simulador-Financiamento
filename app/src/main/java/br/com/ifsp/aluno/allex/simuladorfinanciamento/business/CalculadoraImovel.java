package br.com.ifsp.aluno.allex.simuladorfinanciamento.business;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.model.Financiamento;

public final class CalculadoraImovel extends CalculadoraFinanciamento {

    public CalculadoraImovel() {
        super(.2);
    }

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
            financiamento.setValorFinal(aplicaTaxasAdicionaisValorNovo(financiamento.getValorFinal()));
        else
            financiamento.setValorFinal(aplicaTaxasAdicionaisValorUsado(financiamento.getValorFinal()));

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

    @Override
    public Double aplicaTaxasAdicionaisValorNovo(Double valor) {
        return valor + calculaHabitese(valor);
    }

    @Override
    public Double aplicaTaxasAdicionaisValorUsado(Double valor) {
        return valor + calculaTransferencia(valor);
    }

    private Double calculaTransferencia(Double valor) {
        return valor * .02;
    }

    private Double calculaHabitese(Double valor) {
        return valor * .05;
    }

    public Double determinaTaxaJuros(Double renda) {
        if(renda <= 3500)
            return .03;
        else if(renda <= 5000)
            return .025;
        else
            return .02;
    }

}
