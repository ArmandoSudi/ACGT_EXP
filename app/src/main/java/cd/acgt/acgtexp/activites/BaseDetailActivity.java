package cd.acgt.acgtexp.activites;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cd.acgt.acgtexp.utils.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.ui.DetailsProprieteFragment;
import cd.acgt.acgtexp.ui.DetailsRiverainFragment;

public class BaseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Intent intent = getIntent();
        int type = intent.getIntExtra(Constant.KEY_TYPE, 100);
        long codeRiverain = intent.getLongExtra(Constant.KEY_CODE_RIVERAIN, 0);
        final long codePropriete = intent.getLongExtra(Constant.KEY_CODE_PROPRIETE, 0);
        switch(type) {
            case Constant.PROPRIETE_TYPE:
                fragmentManager.beginTransaction().
                        add(R.id.fragment_container, DetailsProprieteFragment.newInstance(codePropriete)).
                        commit();
                break;

                case Constant.RIVERAIN_TYPE:
                    fragmentManager.beginTransaction().
                            add(R.id.fragment_container, DetailsRiverainFragment.newInstance(codeRiverain)).commit();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(BaseDetailActivity.this, BaseAddActivity.class);
                intent.putExtra(Constant.KEY_TYPE, Constant.TYPE_PROPRIETE_TYPE);
                intent.putExtra(Constant.KEY_CODE_PROPRIETE, codePropriete);
                startActivity(intent);
            }
        });
    }
}
