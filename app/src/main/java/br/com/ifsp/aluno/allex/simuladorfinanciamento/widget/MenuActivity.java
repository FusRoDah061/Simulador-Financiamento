package br.com.ifsp.aluno.allex.simuladorfinanciamento.widget;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.Constants;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.MainActivity;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.R;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.SimularAutoActivity;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.SimularImovelActivity;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.SobreActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_inicio:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;

            case R.id.action_sim_veiculo:
                startActivity(new Intent(this, SimularAutoActivity.class));
                finish();
                return true;

            case R.id.action_sim_residencia:
                startActivity(new Intent(this, SimularImovelActivity.class));
                finish();
                return true;

            case R.id.action_sobre:
                startActivity(new Intent(this, SobreActivity.class));
                return true;

            case R.id.action_sair:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Constants.EXTRA_SAIR, true);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
