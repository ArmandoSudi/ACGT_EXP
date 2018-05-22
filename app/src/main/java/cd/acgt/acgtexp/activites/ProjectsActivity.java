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
import java.util.List;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.adapters.ProjectAdapter;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Projet;
import cd.acgt.acgtexp.entites.Propriete;

public class ProjectsActivity extends AppCompatActivity {

    private static final String TAG = "ProjectsActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        AcgtExpDatabase.getDatabase(this);

        RecyclerView projetRV = findViewById(R.id.projet_rv);
        ProjectAdapter projetAdatper = new ProjectAdapter(this, populateProjet());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false );
        projetRV.setLayoutManager(linearLayoutManager);
        projetRV.setHasFixedSize(true);
        projetRV.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
        projetRV.setAdapter(projetAdatper);
    }

    public List<Projet> populateProjet(){
        List<Projet> projets = new ArrayList<>();

        projets.add(new Projet("Autoroute Aeoroport"));
        projets.add(new Projet("Matadi - Kinshasa "));
        projets.add(new Projet("Bukavu - kamanyola"));
        projets.add(new Projet("Tramway kinshasa"));

        return projets;
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
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            testDB();
        }

        return super.onOptionsItemSelected(item);
    }

    public void testDB() {
        (new AsyncTask<Void, Void, long[]>() {
            @Override
            protected long[] doInBackground(Void... voids) {
                Propriete propriete = new Propriete("batis", "Av nguma no 4", "1001");

                long[] rows = AcgtExpDatabase.getInstance().getIProprieteDao().insert(propriete);
                AcgtExpDatabase.getInstance().getIProprieteDao().deleteAll();

                return rows;
            }

            @Override
            protected void onPostExecute(long[] aLong) {
                super.onPostExecute(aLong);

                Log.e(TAG, "onPostExecute: " + aLong[0] );
                Log.e(TAG, "onPostExecute: " + aLong.length );
//                Log.e(TAG, "onPostExecute: " + aLong );
            }
        }).execute();
    }
}
