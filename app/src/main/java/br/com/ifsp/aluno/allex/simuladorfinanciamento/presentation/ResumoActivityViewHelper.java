package br.com.ifsp.aluno.allex.simuladorfinanciamento.presentation;

import java.security.InvalidParameterException;
import java.text.NumberFormat;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.R;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.model.Financiamento;

public class ResumoActivityViewHelper {

    private Financiamento model;

    public ResumoActivityViewHelper(Financiamento model) {
        this.model = model;
    }

    private String formataDinheiro(Double valor) {
        return NumberFormat.getCurrencyInstance().format(valor);
    }

    public int getIcone() {
        switch (model.getTipo()) {
            case FINANCIAMENTO_AUTOMOVEL:
                return R.drawable.car;
            case FINANCIAMENTO_IMOVEL:
                return R.drawable.house;
            default:
                throw new InvalidParameterException("Tipo de financiamento inválido");
        }
    }

    public String getValorFinal() {
        return formataDinheiro(model.getValorFinal());
    }

    public String getDetalheCabecalho() {
        return String.format("%d x %s", model.getQtdParcelas(), formataDinheiro(model.getValorParcelas()));
    }

    public String getValor() {
        return formataDinheiro(model.getValor());
    }

    public String getValorEntrada() {
        return formataDinheiro(model.getEntrada());
    }

    public String getRendaLiquida() {
        return formataDinheiro(model.getRendaMensal());
    }

    public String getEstado() {
        return model.isNovo() ? "NOVO" : "USADO";
    }

    public String getQtdParcelas() {
        return String.valueOf(model.getQtdParcelas());
    }

    public String getTaxaJuros() {
        return String.format("%.2f%% a.m.", model.getTaxaJuros() * 100);
    }

    public String getValorParcela() {
        return formataDinheiro(model.getValorParcelas());
    }
}
