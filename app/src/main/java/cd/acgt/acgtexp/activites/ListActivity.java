package cd.acgt.acgtexp.activites;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import cd.acgt.acgtexp.utils.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.ui.RiverainListFragment;
import cd.acgt.acgtexp.ui.ProprieteListFragment;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Getting the codeProjet in order to pass it to the propriete/riverain when creating the
        // fragment
        Intent intent = getIntent();
        final String codeProjet = intent.getStringExtra(Constant.KEY_CODE_PROJECT);
        Toast.makeText(this, "" + codeProjet, Toast.LENGTH_LONG).show();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(ProprieteListFragment.newInstance(codeProjet), "propriete").commit();

        mFab = findViewById(R.id.fab);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, BaseAddActivity.class);

                intent.putExtra(Constant.KEY_TYPE, Constant.PROPRIETE_TYPE);
                intent.putExtra(Constant.KEY_CODE_PROJECT, codeProjet);
                startActivity(intent);

                // FOR ADDING A RIVERAIN FOR A PARTICULAR PROJECT
                // intent.putExtra(Constant.KEY_TYPE, Constant.RIVERAIN_TYPE);
                // intent.putExtra(Constant.KEY_CODE_PROJECT, codeProjet);
                // startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_map) {
            Toast.makeText(this, "map", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
