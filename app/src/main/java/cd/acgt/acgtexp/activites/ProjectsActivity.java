package cd.acgt.acgtexp.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.adapters.ProjectAdapter;
import cd.acgt.acgtexp.entites.Projet;

public class ProjectsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

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
}
