package cd.acgt.acgtexp.activites;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.adapters.ProjectAdapter;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Projet;
import cd.acgt.acgtexp.entites.Propriete;
import cd.acgt.acgtexp.entites.Riverain;

public class ProjectsActivity extends AppCompatActivity {

    private static final String TAG = "ProjectsActivity";

    ProjectAdapter mProjetcAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        new LoadProjectAsyncTask(mProjetcAdapter).execute();
        if (mProjetcAdapter != null) {
            mProjetcAdapter.clear();
            mProjetcAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        AcgtExpDatabase.getDatabase(this);

        RecyclerView projetRV = findViewById(R.id.projet_rv);
        mProjetcAdapter = new ProjectAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false );
        projetRV.setLayoutManager(linearLayoutManager);
        projetRV.setHasFixedSize(true);
        projetRV.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
        projetRV.setAdapter(mProjetcAdapter);
    }

    public void populateProjet(){
        (new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<Projet> projets = new ArrayList<>();
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, 2018);
                cal.set(Calendar.MONTH, 10);
                cal.set(Calendar.DAY_OF_MONTH, 10);
                Date date = cal.getTime();

                projets.add(new Projet("1002","Autoroute Aeoroport", date, date, date,date, date));
                projets.add(new Projet("1003","Matadi - Kinshasa ", date, date, date, date, date));
                projets.add(new Projet("1004","Bukavu - kamanyola", date, date, date, date, date));
                projets.add(new Projet("1005","Tramway kinshasa", date, date, date, date, date));

                for (Projet projet : projets) {
                    AcgtExpDatabase.getInstance().getIProjetDao().insert(projet);
                }
                return null;
            }
        }).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_projet, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_test) {
            populateProjet();
        } else if (id == R.id.action_clear) {
            new LoadProjectAsyncTask(mProjetcAdapter).execute();
        }

        return super.onOptionsItemSelected(item);
    }

    public void testDB() {
        (new AsyncTask<Void, Void, long[]>() {
            @Override
            protected long[] doInBackground(Void... voids) {

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, 2018);
                cal.set(Calendar.MONTH, 10);
                cal.set(Calendar.DAY_OF_MONTH, 10);
                Date date = cal.getTime();

                Propriete propriete = new Propriete("Av Nguma", "batis", "url one", "url two", "url three",  1, "1001", 0.0, 0.0 );
                Projet projet = new Projet("1001","Projet 1", date, date, date, date, date);
                projet.setCodeProjet("1001");
                Riverain riverain = new Riverain("John Doe", "Av de la paix", "09999999", "batis", "autre info",
                        "PM", "pas de rep", "Passeport", "1111", "www.google.com", "rccm", "123456", "1001");

                long[] rowPropriete = AcgtExpDatabase.getInstance().getIProprieteDao().insert(propriete);
                long[] rowProjet = AcgtExpDatabase.getInstance().getIProjetDao().insert(projet);
                long[] rowRiverain = AcgtExpDatabase.getInstance().getIRiverainDao().insert(riverain);

                long[] rows = {rowPropriete[0], rowProjet[0], rowRiverain[0]};

                AcgtExpDatabase.getInstance().getIProprieteDao().deleteAll();
                AcgtExpDatabase.getInstance().getIProjetDao().deleteAll();
                AcgtExpDatabase.getInstance().getIRiverainDao().deleteAll();

                return rows;
            }

            @Override
            protected void onPostExecute(long[] aLong) {
                super.onPostExecute(aLong);

                Log.e(TAG, "onPostExecute: " + aLong.length );
                Log.e(TAG, "onPostExecute: " + aLong[0] );
                Log.e(TAG, "onPostExecute: " + aLong[1] );
                Log.e(TAG, "onPostExecute: " + aLong[2] );
            }
        }).execute();
    }

    static class LoadProjectAsyncTask extends AsyncTask<Void, Void, List<Projet>> {

        ProjectAdapter projectAdapter;

        public LoadProjectAsyncTask(ProjectAdapter projectAdapter) {
            this.projectAdapter = projectAdapter;
        }

        @Override
        protected void onPostExecute(List<Projet> projets) {
            super.onPostExecute(projets);
            projectAdapter.addProjets(projets);
            projectAdapter.notifyDataSetChanged();

        }

        @Override
        protected List<Projet> doInBackground(Void... voids) {
            return AcgtExpDatabase.getInstance().getIProjetDao().getAll();
        }
    }
}
