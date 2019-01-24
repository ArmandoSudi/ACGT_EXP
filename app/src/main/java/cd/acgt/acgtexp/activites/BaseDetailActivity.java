package cd.acgt.acgtexp.activites;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cd.acgt.acgtexp.utils.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.ui.DetailsProprieteFragment;
import cd.acgt.acgtexp.ui.DetailsRiverainFragment;

public class BaseDetailActivity extends AppCompatActivity {
    private static final String TAG = "BaseDetailActivity";

    int mCodePropriete;

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
        final int codePropriete = intent.getIntExtra(Constant.KEY_CODE_PROPRIETE, 0);
        mCodePropriete = codePropriete;
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

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent  = new Intent(BaseDetailActivity.this, BaseAddActivity.class);
//                intent.putExtra(Constant.KEY_TYPE, Constant.TYPE_PROPRIETE_TYPE);
//                intent.putExtra(Constant.KEY_CODE_PROPRIETE, codePropriete);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details_propriete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_person) {
            Intent intent = new Intent(this, BaseAddActivity.class);
//            intent.putExtra(Constant.KEY_CODE_PROJECT, mCodeProjet);
            intent.putExtra(Constant.KEY_TYPE, Constant.RIVERAIN_TYPE);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
