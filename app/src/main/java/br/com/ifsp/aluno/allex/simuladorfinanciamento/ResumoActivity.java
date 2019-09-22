package br.com.ifsp.aluno.allex.simuladorfinanciamento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.model.Financiamento;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.model.FinanciamentoAutomovel;

public class ResumoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        Bundle extras = getIntent().getExtras();
        Financiamento financiamento;

        if(Constants.TIPO_FINANCIAMENTO_AUTO.equals(extras.getString(Constants.EXTRA_TIPO_FINANCIAMENTO))) {
            financiamento = (FinanciamentoAutomovel) extras.getParcelable(Constants.EXTRA_FINANCIAMENTO);
        }
        else {
            financiamento = (Financiamento) extras.getParcelable(Constants.EXTRA_FINANCIAMENTO);
        }
    }
}
