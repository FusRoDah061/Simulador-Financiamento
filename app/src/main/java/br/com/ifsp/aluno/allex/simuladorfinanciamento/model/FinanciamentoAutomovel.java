package br.com.ifsp.aluno.allex.simuladorfinanciamento.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.Constants;

public class FinanciamentoAutomovel extends Financiamento implements Parcelable {

    private boolean isNovo;

    public FinanciamentoAutomovel(Double valor, Double entrada, int qtdParcelas, double rendaMensal, boolean isNovo) {
        super(valor, entrada, qtdParcelas, rendaMensal);
        this.isNovo = isNovo;
    }

    private FinanciamentoAutomovel(Parcel in) {
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

    public static final Parcelable.Creator<FinanciamentoAutomovel> CREATOR = new Parcelable.Creator<FinanciamentoAutomovel>() {
        public FinanciamentoAutomovel createFromParcel(Parcel in) {
            return new FinanciamentoAutomovel(in);
        }

        public FinanciamentoAutomovel[] newArray(int size) {
            return new FinanciamentoAutomovel[size];
        }
    };
}
