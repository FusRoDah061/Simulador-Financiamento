package br.com.ifsp.aluno.allex.simuladorfinanciamento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.model.Financiamento;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.presentation.ResumoActivityViewHelper;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.widget.MenuActivity;

public class ResumoActivity extends MenuActivity implements View.OnClickListener {

    private Globals globals = Globals.getInstance();

    private TextView tvNomeUsuario;

    private ImageView imvIcone;

    private TextView tvValorTotalDestaque;
    private TextView tvDetalheDestaque;

    private TextView tvValorBem;
    private TextView tvValorEntrada;
    private TextView tvRendaLiquida;
    private TextView tvEstadoBem;
    private TextView tvQtdParcelas;
    private TextView tvTaxaJuros;
    private TextView tvValorParcela;
    private TextView tvValorTotal;

    private Button btnNovaSimulacao;

    private ResumoActivityViewHelper viewHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        initComponents();
        preencheResumo();
    }

    private void preencheResumo() {
        Bundle extras = getIntent().getExtras();
        Financiamento financiamento = extras.getParcelable(Constants.EXTRA_FINANCIAMENTO);

        viewHelper = new ResumoActivityViewHelper(financiamento);

        exibeCabecalho(financiamento);
        exibeResumo(financiamento);
    }

    private void exibeResumo(Financiamento financiamento) {
        tvValorBem.setText(viewHelper.getValor());
        tvValorEntrada.setText(viewHelper.getValorEntrada());
        tvRendaLiquida.setText(viewHelper.getRendaLiquida());
        tvEstadoBem.setText(viewHelper.getEstado());
        tvQtdParcelas.setText(viewHelper.getQtdParcelas());
        tvTaxaJuros.setText(viewHelper.getTaxaJuros());
        tvValorParcela.setText(viewHelper.getValorParcela());
        tvValorTotal.setText(viewHelper.getValorFinal());
    }

    private void exibeCabecalho(Financiamento financiamento) {
        imvIcone.setImageResource(viewHelper.getIcone());
        tvValorTotalDestaque.setText(viewHelper.getValorFinal());
        tvDetalheDestaque.setText(viewHelper.getDetalheCabecalho());
    }

    private void initComponents() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        tvNomeUsuario = (TextView) findViewById(R.id.tvNomeUsuario);
        tvNomeUsuario.setText("Olá, " + globals.getNomeUsuario());

        imvIcone = (ImageView) findViewById(R.id.imvIcone);

        tvValorTotalDestaque = (TextView) findViewById(R.id.tvValorTotalDestaque);
        tvDetalheDestaque = (TextView) findViewById(R.id.tvDetalheDestaque);

        tvValorBem = (TextView) findViewById(R.id.tvValorBem);
        tvValorEntrada = (TextView) findViewById(R.id.tvValorEntrada);
        tvRendaLiquida = (TextView) findViewById(R.id.tvRendaLiquida);
        tvEstadoBem = (TextView) findViewById(R.id.tvEstadoBem);
        tvQtdParcelas = (TextView) findViewById(R.id.tvQtdParcelas);
        tvTaxaJuros = (TextView) findViewById(R.id.tvTaxaJuros);
        tvValorParcela = (TextView) findViewById(R.id.tvValorParcela);
        tvValorTotal = (TextView) findViewById(R.id.tvValorTotal);

        btnNovaSimulacao = (Button) findViewById(R.id.btnNovaSimulacao);
        btnNovaSimulacao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnNovaSimulacao.getId()){
            Intent intent = new Intent(ResumoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
