package br.com.ifsp.aluno.allex.simuladorfinanciamento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.business.CalculadoraAutomovel;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.model.Financiamento;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.widget.LabeledEditText;

public class SimularAutoActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private Globals globals = Globals.getInstance();
    private CalculadoraAutomovel calculadoraAutomovel = new CalculadoraAutomovel();

    private TextView tvNomeUsuario;
    private TextView tvHintEntradaMinima;
    private RadioButton rbtNovo;
    private LabeledEditText letValorAutomovel;
    private LabeledEditText letValorEntrada;
    private LabeledEditText letQtdParcelas;
    private LabeledEditText letRendaMensal;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simular_auto);
        initComponents();
    }

    private void initComponents() {
        tvNomeUsuario = (TextView) findViewById(R.id.tvNomeUsuario);
        tvHintEntradaMinima = (TextView) findViewById(R.id.tvHintEntradaMinima);
        rbtNovo = (RadioButton) findViewById(R.id.rbtNovo);
        letValorAutomovel = (LabeledEditText) findViewById(R.id.letValorAutomovel);
        letValorEntrada = (LabeledEditText) findViewById(R.id.letValorEntrada);
        letQtdParcelas = (LabeledEditText) findViewById(R.id.letQtdParcelas);
        letRendaMensal = (LabeledEditText) findViewById(R.id.letRendaMensal);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        
        tvNomeUsuario.setText("Olá, " + globals.getNomeUsuario());

        letValorAutomovel.setOnFocusChangeListener(this);

        btnCalcular.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnCalcular.getId()) {
            calcularFinanciamento();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v.getId() == letValorAutomovel.getInnerEditTextId() && !hasFocus && letValorAutomovel.getValue() > 0) {
            double entrada = calculadoraAutomovel.calculaEntradaMinima(letValorAutomovel.getValue());
            tvHintEntradaMinima.setText(String.format("Valor mínimo de R$ %.2f", entrada));
        }
    }

    private void calcularFinanciamento() {
        Financiamento financiamento = new Financiamento(
                letValorAutomovel.getValue(),
                letValorEntrada.getValue(),
                letQtdParcelas.getValue().intValue(),
                letRendaMensal.getValue(),
                rbtNovo.isChecked()
        );

        if(calculadoraAutomovel.calculaFinanciamento(financiamento)) {
            Intent intent = new Intent(SimularAutoActivity.this, ResumoActivity.class);
            intent.putExtra(Constants.EXTRA_FINANCIAMENTO, financiamento);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, calculadoraAutomovel.getUltimoErro(), Toast.LENGTH_LONG).show();
        }
    }
}
