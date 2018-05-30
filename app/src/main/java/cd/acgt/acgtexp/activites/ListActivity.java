package cd.acgt.acgtexp.activites;

import android.content.Intent;
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

import android.widget.Toast;

import cd.acgt.acgtexp.utils.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.ui.RiverainListFragment;
import cd.acgt.acgtexp.ui.ProprieteListFragment;

public class ListActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private FloatingActionButton mFab;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Getting the codeProjet in order to pass it to the propriete/riverain when creating the
        // fragment
        Intent intent = getIntent();
        final String codeProjet = intent.getStringExtra(Constant.KEY_CODE_PROJECT);
        Toast.makeText(this, "" + codeProjet, Toast.LENGTH_LONG).show();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), codeProjet);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mFab = findViewById(R.id.fab);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabID = tab.getPosition();
                switch(tabID){
                    case 0:
                        mFab.setImageResource(R.drawable.ic_add);
                        break;
                    case 1:
                        mFab.setImageResource(R.drawable.ic_map);
                        break;
                    default:
                        mFab.setImageResource(R.drawable.ic_add);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(ListActivity.this, BaseAddActivity.class);
                switch(mViewPager.getCurrentItem()){
                    case 0:
                        // riverrain
                        intent.putExtra(Constant.KEY_TYPE, Constant.RIVERAIN_TYPE);
                        intent.putExtra(Constant.KEY_CODE_PROJECT, codeProjet);
                        startActivity(intent);
                        break;
                    case 1:
                        // propriete
                        intent.putExtra(Constant.KEY_TYPE, Constant.PROPRIETE_TYPE);
                        intent.putExtra(Constant.KEY_CODE_PROJECT, codeProjet);
                        startActivity(intent);
                        break;
                }
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

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        String mCodeProjet;

        public SectionsPagerAdapter(FragmentManager fm, String codeProjet) {
            super(fm);
            this.mCodeProjet = codeProjet;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return RiverainListFragment.newInstance(mCodeProjet);
                case 1:
                    return ProprieteListFragment.newInstance(mCodeProjet);
                default:
                    return RiverainListFragment.newInstance(mCodeProjet);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}
