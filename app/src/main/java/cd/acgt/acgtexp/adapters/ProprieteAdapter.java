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
import cd.acgt.acgtexp.activites.ListActivity;
import cd.acgt.acgtexp.entites.Projet;
import cd.acgt.acgtexp.entites.Propriete;

/**
 * Created by Sugar on 5/21/2018
 */
public class ProprieteAdapter extends RecyclerView.Adapter<ProprieteAdapter.VH> {

    Activity mActivity;
    List<Propriete> mProprietes = new ArrayList<>();

    public static class VH extends RecyclerView.ViewHolder {
        TextView adresseTV;

        public VH(View view) {
            super(view);
            adresseTV = view.findViewById(R.id.adresse_tv);
        }
    }

    public ProprieteAdapter(Activity mActivity, List<Propriete> mProprietes) {
        this.mActivity = mActivity;
        this.mProprietes = mProprietes;
    }

    @Override
    public void onBindViewHolder(ProprieteAdapter.VH holder, final int position) {
        final Propriete propriete = mProprietes.get(position);
        holder.adresseTV.setText(propriete.getAdresse());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "position: " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mActivity, ListActivity.class);
                intent.putExtra(Constant.KEY_CODE_PROJECT, propriete.getCodePropriete());
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public ProprieteAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.propriete_item, parent, false);

        return new ProprieteAdapter.VH(view);
    }

    @Override
    public int getItemCount() {
        return mProprietes.size();
    }
}
