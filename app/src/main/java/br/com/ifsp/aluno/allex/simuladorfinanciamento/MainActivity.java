package br.com.ifsp.aluno.allex.simuladorfinanciamento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Globals globals = Globals.getInstance();

    private Button btnSimular;
    private RadioButton rbtAutomovel;
    private RadioButton rbtImovel;
    private EditText edtNomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSimular = (Button) findViewById(R.id.btnSimular);
        rbtAutomovel = (RadioButton) findViewById(R.id.rbtAutomovel);
        rbtImovel = (RadioButton) findViewById(R.id.rbtImovel);
        edtNomeUsuario = (EditText) findViewById(R.id.edtNomeUsuario);

        btnSimular.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnSimular.getId()) {
            String nomeUsuario = edtNomeUsuario.getText().toString();

            if(nomeUsuario == null || nomeUsuario.isEmpty()) {
                Toast.makeText(this, "Por favor nos diga seu nome.", Toast.LENGTH_SHORT).show();
                return;
            }

            globals.setNomeUsuario(nomeUsuario);
            iniciarSimulacao();
        }
    }

    private void iniciarSimulacao () {
        Intent intent = null;

        if(rbtAutomovel.isChecked()) {
            intent = new Intent(MainActivity.this, SimularAutoActivity.class);
        }
        else if (rbtImovel.isChecked()){
            intent = new Intent(MainActivity.this, SimularImovelActivity.class);
        }

        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
    }
}
