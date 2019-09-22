package br.com.ifsp.aluno.allex.simuladorfinanciamento.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.Constants;

public class Financiamento implements Parcelable {

    private Double valor;
    private Double valorFinal;
    private Double entrada;
    private int qtdParcelas;
    private Double taxaJuros;
    private Double valorParcelas;
    private Double rendaMensal;
    private boolean isNovo;

    public Financiamento(){}

    public Financiamento(Double valor, Double entrada, int qtdParcelas, double rendaMensal, boolean isNovo) {
        this.valor = valor;
        this.valorFinal = valor;
        this.entrada = entrada;
        this.qtdParcelas = qtdParcelas;
        this.rendaMensal = rendaMensal;
        this.isNovo = isNovo;
        this.taxaJuros = 0.0;
        this.valorParcelas = 0.0;
    }

    private Financiamento(Parcel in) {
        super();
        Bundle content = in.readBundle();

        this.setValor(content.getDouble(Constants.PARCELABLE_VALOR));
        this.setValorFinal(content.getDouble(Constants.PARCELABLE_VALOR_FINAL));
        this.setEntrada(content.getDouble(Constants.PARCELABLE_ENTRADA));
        this.setQtdParcelas(content.getInt(Constants.PARCELABLE_QTD_PARCELAS));
        this.setTaxaJuros(content.getDouble(Constants.PARCELABLE_TAXA_JUROS));
        this.setValorParcelas(content.getDouble(Constants.PARCELABLE_VALOR_PARCELAS));
        this.setRendaMensal(content.getDouble(Constants.PARCELABLE_RENDA_MENSAL));
        this.setNovo(content.getBoolean(Constants.PARCELABLE_IS_NOVO));
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getEntrada() {
        return entrada;
    }

    public void setEntrada(Double entrada) {
        this.entrada = entrada;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public Double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public double getValorParcelas() {
        return valorParcelas;
    }

    public void setValorParcelas(double valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    public double getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public void setValorParcelas(Double valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    public void setRendaMensal(Double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public boolean isNovo() {
        return isNovo;
    }

    public void setNovo(boolean novo) {
        isNovo = novo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        Bundle content = new Bundle(8);

        content.putDouble(Constants.PARCELABLE_VALOR, this.getValor());
        content.putDouble(Constants.PARCELABLE_VALOR_FINAL, this.getValorFinal());
        content.putDouble(Constants.PARCELABLE_ENTRADA, this.getEntrada());
        content.putInt(Constants.PARCELABLE_QTD_PARCELAS, this.getQtdParcelas());
        content.putDouble(Constants.PARCELABLE_TAXA_JUROS, this.getTaxaJuros());
        content.putDouble(Constants.PARCELABLE_VALOR_PARCELAS, this.getValorParcelas());
        content.putDouble(Constants.PARCELABLE_RENDA_MENSAL, this.getRendaMensal());
        content.putBoolean(Constants.PARCELABLE_IS_NOVO, this.isNovo());

        out.writeBundle(content);
    }

    public static final Parcelable.Creator<Financiamento> CREATOR = new Parcelable.Creator<Financiamento>() {
        public Financiamento createFromParcel(Parcel in) {
            return new Financiamento(in);
        }

        public Financiamento[] newArray(int size) {
            return new Financiamento[size];
        }
    };
}
