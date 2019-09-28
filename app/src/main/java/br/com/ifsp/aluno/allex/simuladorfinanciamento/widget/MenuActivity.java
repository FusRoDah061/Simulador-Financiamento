package br.com.ifsp.aluno.allex.simuladorfinanciamento.widget;

import android.app.Application;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.Constants;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.MainActivity;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.R;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.SimularAutoActivity;
import br.com.ifsp.aluno.allex.simuladorfinanciamento.SimularImovelActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.action_inicio:
                intent = new Intent(this, MainActivity.class);
                break;

            case R.id.action_sim_veiculo:
                intent = new Intent(this, SimularAutoActivity.class);
                break;

            case R.id.action_sim_residencia:
                intent = new Intent(this, SimularImovelActivity.class);
                break;

            case R.id.action_sobre:
                // TODO
                break;

            case R.id.action_sair:
                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Constants.EXTRA_SAIR, true);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        if(intent != null) {
            startActivity(intent);
            finish();
            return true;
        }

        return false;
    }

}
