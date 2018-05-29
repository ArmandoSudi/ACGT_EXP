package cd.acgt.acgtexp.activites;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cd.acgt.acgtexp.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.ui.AddProprieteFragment;
import cd.acgt.acgtexp.ui.AddRiverainFragment;

public class BaseAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_add);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Intent intent = getIntent();
        int type = intent.getIntExtra(Constant.KEY_TYPE, Constant.PROPRIETE_TYPE);
        String codeProjet = intent.getStringExtra(Constant.KEY_CODE_PROJECT);
        Toast.makeText(this, "" + codeProjet, Toast.LENGTH_LONG).show();
        switch(type) {
            case Constant.PROPRIETE_TYPE:
                try {
                    getSupportActionBar().setTitle("Ajouter une Propriete");
                } catch (NullPointerException exception){
                    exception.printStackTrace();
                }

                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container, AddProprieteFragment.newInstance(codeProjet)).commit();
                break;
            case Constant.RIVERAIN_TYPE:
                try {
                    getSupportActionBar().setTitle("Ajouter un Riverain");
                } catch (NullPointerException exception) {
                    exception.printStackTrace();
                }

                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container, AddRiverainFragment.newInstance(codeProjet)).commit();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
