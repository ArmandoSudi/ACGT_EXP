package cd.acgt.acgtexp.activites;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Projet;
import cd.acgt.acgtexp.service.ExpApi;
import cd.acgt.acgtexp.service.ExpApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectUserActivity extends AppCompatActivity {

    private static final String TAG = "SelectUserActivity";

    private ExpApiInterface expApiInterface = ExpApi.getService();

    Spinner mUserSP;
    Button mButton;

    @Override
    protected void onStart() {
        super.onStart();

        AcgtExpDatabase.getDatabase(this);
        initializeData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        initView();
    }

    public void initView() {
        mButton = findViewById(R.id.suivant_bt);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectUserActivity.this, ProjectsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initializeData() {
        expApiInterface.getProjets().enqueue(new Callback<List<Projet>>() {
            @Override
            public void onResponse(Call<List<Projet>> call, Response<List<Projet>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Projet> projets = response.body();
                        for (Projet projet : projets) {
                            Log.e(TAG, projet.toString() );
                        }
                        saveInDB(projets);
                    } else {
                        Log.e(TAG, "getProjets, response body is NULL");
                    }
                } else {
                    Log.e(TAG, "getProjets, response is successful");
                }
            }

            @Override
            public void onFailure(Call<List<Projet>> call, Throwable t) {
                Log.e(TAG, "getProjets FAILED: " + t.getMessage() );
            }
        });
    }

    public void saveInDB(final List<Projet> projets) {
        (new AsyncTask<List<Projet>, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.e(TAG, "onPostExecute: Projets ");
            }

            @Override
            protected Void doInBackground(List<Projet>... lists) {
                AcgtExpDatabase.getInstance().getIProjetDao().insertAll(projets);
                return null;
            }
        }).execute(projets);
    }
}
