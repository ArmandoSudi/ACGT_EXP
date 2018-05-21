package cd.acgt.acgtexp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cd.acgt.acgtexp.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.entites.Projet;
import cd.acgt.acgtexp.activites.ListActivity;

/**
 * Created by Sugar on 5/18/2018
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.VH> {

    Activity mActivity;
    List<Projet> mProjets = new ArrayList<>();

    static class VH extends RecyclerView.ViewHolder {
        TextView nomTV;

        public VH(View view) {
            super(view);
            nomTV = view.findViewById(R.id.nom_tv);
        }
    }

    public ProjectAdapter(Activity activity, List<Projet> projets){
        this.mActivity = activity;
        this.mProjets = projets;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        final Projet projet = mProjets.get(position);
        holder.nomTV.setText(projet.getNom());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "position: " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mActivity, ListActivity.class);
                intent.putExtra(Constant.KEY_CODE_PROJECT, projet.getCodeProjet());
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.projet_item, parent, false);

        return new VH(view);
    }

    @Override
    public int getItemCount() {
        return mProjets.size();
    }
}
