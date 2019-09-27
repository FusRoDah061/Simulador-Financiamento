package br.com.ifsp.aluno.allex.simuladorfinanciamento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.model.Financiamento;

public class ResumoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        Bundle extras = getIntent().getExtras();
        Financiamento financiamento = extras.getParcelable(Constants.EXTRA_FINANCIAMENTO);
    }
}
