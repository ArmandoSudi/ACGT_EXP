package cd.acgt.acgtexp.activites;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.adapters.ProjectAdapter;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Litige;
import cd.acgt.acgtexp.entites.Paiement;
import cd.acgt.acgtexp.entites.Projet;
import cd.acgt.acgtexp.service.ExpApi;
import cd.acgt.acgtexp.service.ExpApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectsActivity extends AppCompatActivity {

    private static final String TAG = "ProjectsActivity";
    private ExpApiInterface mExpApiService = ExpApi.getService();
    List<Projet> mProjets = new ArrayList<>();

    ProjectAdapter mProjetcAdapter = new ProjectAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        AcgtExpDatabase.getDatabase(this);

        new LoadProjectAsyncTask(mProjetcAdapter).execute();

        RecyclerView projetRV = findViewById(R.id.projet_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false );
        projetRV.setLayoutManager(linearLayoutManager);
        projetRV.setHasFixedSize(true);
        projetRV.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
        projetRV.setAdapter(mProjetcAdapter);
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
        if (id == R.id.action_get_project) {
            collectProjet();
        } else if (id == R.id.action_synchronization) {
            Intent intent = new Intent(this, SynchronizationActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void collectProjet() {

        mExpApiService.getProjets().enqueue(new Callback<List<Projet>>() {
            @Override
            public void onResponse(Call<List<Projet>> call, Response<List<Projet>> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Projet> projets = response.body();
                        for (Projet projet : projets) {
                            Log.e(TAG, "collectProjets: " + projet.shortDesignation );
                        }
                        insertInDB(projets);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Projet>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
                Log.e(TAG, "onFailure: " + t.toString() );
            }
        });
    }

    public void insertInDB(final List<Projet> projets){
        (new AsyncTask<List<Projet>, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                //After inserting successfully in the DB, retrieve the data
                new LoadProjectAsyncTask(mProjetcAdapter).execute();
            }

            @Override
            protected Void doInBackground(List<Projet>... lists) {
                AcgtExpDatabase.getInstance().getIProjetDao().insertAll(projets);
                return null;
            }
        }).execute(projets);
    }

    static class LoadProjectAsyncTask extends AsyncTask<Void, Void, List<Projet>> {

        ProjectAdapter projectAdapter;

        public LoadProjectAsyncTask(ProjectAdapter projectAdapter) {
            this.projectAdapter = projectAdapter;
        }

        @Override
        protected void onPostExecute(List<Projet> projets) {
            super.onPostExecute(projets);

            Log.e(TAG, "LoadProjet, projets size: " + projets.size());

            projectAdapter.addProjets(projets);
            projectAdapter.notifyDataSetChanged();
        }

        @Override
        protected List<Projet> doInBackground(Void... voids) {
            return AcgtExpDatabase.getInstance().getIProjetDao().getAll();
        }
    }
}
