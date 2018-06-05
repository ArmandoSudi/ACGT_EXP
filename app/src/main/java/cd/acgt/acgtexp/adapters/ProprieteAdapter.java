package cd.acgt.acgtexp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cd.acgt.acgtexp.dao.IProprieteItemDao;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Riverain;
import cd.acgt.acgtexp.utils.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.activites.BaseDetailActivity;
import cd.acgt.acgtexp.entites.Propriete;

/**
 * Created by Sugar on 5/21/2018
 */
public class ProprieteAdapter extends RecyclerView.Adapter<ProprieteAdapter.VH> {

    Activity mActivity;
    List<Propriete> mProprietes;
    List<IProprieteItemDao.ProprieteItem> mProprieteItem;

    public static class VH extends RecyclerView.ViewHolder {
        TextView adresseTV, proprietaireTV, typeTV;

        public VH(View view) {
            super(view);
            adresseTV = view.findViewById(R.id.adresse_tv);
            typeTV = view.findViewById(R.id.type_tv);
            proprietaireTV = view.findViewById(R.id.proprietaire_tv);
        }
    }

    public ProprieteAdapter(Activity mActivity) {
        this.mActivity = mActivity;
        this.mProprietes = new ArrayList<>();
        this.mProprieteItem = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(ProprieteAdapter.VH holder, final int position) {

//        final Propriete propriete = mProprietes.get(position);
        final IProprieteItemDao.ProprieteItem proprieteItem = mProprieteItem.get(position);
        holder.adresseTV.setText(proprieteItem.adresse);
        holder.typeTV.setText(proprieteItem.type);
        holder.proprietaireTV.setText(proprieteItem.proprietaire);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "position: " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mActivity, BaseDetailActivity.class);
                intent.putExtra(Constant.KEY_CODE_PROJECT, proprieteItem.codePropriete);
                intent.putExtra(Constant.KEY_TYPE, Constant.PROPRIETE_TYPE);
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

    public void add(List<IProprieteItemDao.ProprieteItem> proprieteItems) {
        mProprieteItem.addAll(proprieteItems);
    }

    public void clear() {
        mProprieteItem.clear();
    }

    @Override
    public int getItemCount() {
        return mProprieteItem.size();
    }

}
