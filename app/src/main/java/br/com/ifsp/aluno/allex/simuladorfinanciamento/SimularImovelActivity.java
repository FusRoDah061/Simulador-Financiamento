package br.com.ifsp.aluno.allex.simuladorfinanciamento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.business.CalculadoraImovel;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.enums.TipoFinanciamento;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.model.Financiamento;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.widget.LabeledEditText;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.widget.MenuActivity;

public class SimularImovelActivity extends MenuActivity implements View.OnClickListener, View.OnFocusChangeListener  {

    private Globals globals = Globals.getInstance();
    private CalculadoraImovel calculadoraImovel = new CalculadoraImovel();

    private TextView tvNomeUsuario;
    private TextView tvHintEntradaMinima;
    private RadioButton rbtNovo;
    private LabeledEditText letValorImovel;
    private LabeledEditText letValorEntrada;
    private LabeledEditText letQtdParcelas;
    private LabeledEditText letRendaMensal;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simular_imovel);
        initComponents();
    }

    private void initComponents() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        tvNomeUsuario = (TextView) findViewById(R.id.tvNomeUsuario);
        tvHintEntradaMinima = (TextView) findViewById(R.id.tvHintEntradaMinima);
        rbtNovo = (RadioButton) findViewById(R.id.rbtNovo);
        letValorImovel = (LabeledEditText) findViewById(R.id.letValorImovel);
        letValorEntrada = (LabeledEditText) findViewById(R.id.letValorEntrada);
        letQtdParcelas = (LabeledEditText) findViewById(R.id.letQtdParcelas);
        letRendaMensal = (LabeledEditText) findViewById(R.id.letRendaMensal);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);

        tvNomeUsuario.setText("Olá, " + globals.getNomeUsuario());

        letValorImovel.setOnFocusChangeListener(this);

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
        if(v.getId() == letValorImovel.getInnerEditTextId() && !hasFocus && letValorImovel.getValue() > 0) {
            double entrada = calculadoraImovel.calculaEntradaMinima(letValorImovel.getValue());
            tvHintEntradaMinima.setText(String.format("Valor mínimo de R$ %.2f", entrada));
        }
    }

    private void calcularFinanciamento() {
        Financiamento financiamento = new Financiamento(
                TipoFinanciamento.FINANCIAMENTO_IMOVEL,
                letValorImovel.getValue(),
                letValorEntrada.getValue(),
                letQtdParcelas.getValue().intValue(),
                letRendaMensal.getValue(),
                rbtNovo.isChecked()
        );

        if(calculadoraImovel.calculaFinanciamento(financiamento)) {
            Intent intent = new Intent(SimularImovelActivity.this, ResumoActivity.class);
            intent.putExtra(Constants.EXTRA_FINANCIAMENTO, financiamento);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, calculadoraImovel.getUltimoErro(), Toast.LENGTH_LONG).show();
        }
    }
}
