package cd.acgt.acgtexp.activites;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        switch(type) {
            case Constant.PROPRIETE_TYPE:
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container, AddProprieteFragment.newInstance()).commit();
                break;
            case Constant.RIVERAIN_TYPE:
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container, AddRiverainFragment.newInstance()).commit();
                break;
        }
    }
}
