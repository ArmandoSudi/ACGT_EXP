package cd.acgt.acgtexp.activites;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cd.acgt.acgtexp.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.ui.DetailsProprieteFragment;
import cd.acgt.acgtexp.ui.DetailsRiverainFragment;

public class BaseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_detail);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Intent intent = getIntent();
        int type = intent.getIntExtra(Constant.KEY_TYPE, 100);
        switch(type) {
            case Constant.PROPRIETE_TYPE:
                fragmentManager.beginTransaction().
                        add(R.id.fragment_container, DetailsProprieteFragment.newInstance()).
                        commit();
                break;

                case Constant.RIVERAIN_TYPE:
                    fragmentManager.beginTransaction().
                            add(R.id.fragment_container, DetailsRiverainFragment.newInstance()).commit();
        }
    }
}
