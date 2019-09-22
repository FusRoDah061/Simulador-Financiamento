package br.com.ifsp.aluno.allex.simuladorfinanciamento.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public abstract class Financiamento {

    private Double valor;
    private Double valorFinal;
    private Double entrada;
    private int qtdParcelas;
    private Double taxaJuros;
    private Double valorParcelas;
    private Double rendaMensal;

    public Financiamento(){}

    public Financiamento(Double valor, Double entrada, int qtdParcelas, double rendaMensal) {
        this.valor = valor;
        this.valorFinal = valor;
        this.entrada = entrada;
        this.qtdParcelas = qtdParcelas;
        this.rendaMensal = rendaMensal;
        this.taxaJuros = 0.0;
        this.valorParcelas = 0.0;
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

}
