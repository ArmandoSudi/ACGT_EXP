package cd.acgt.acgtexp.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import cd.acgt.acgtexp.activites.BaseAddActivity;
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
    String mCodeProject;

    public static class VH extends RecyclerView.ViewHolder {
        TextView adresseTV, proprietaireTV;
        ImageButton editBT;

        public VH(View view) {
            super(view);
            adresseTV = view.findViewById(R.id.adresse_tv);
            proprietaireTV = view.findViewById(R.id.proprietaire_tv);
            editBT = view.findViewById(R.id.edit_bt);
        }
    }

    public ProprieteAdapter(Activity mActivity, String codeProject) {
        this.mActivity = mActivity;
        this.mProprietes = new ArrayList<>();
        this.mProprieteItem = new ArrayList<>();
        this.mCodeProject = codeProject;
    }

    @Override
    public void onBindViewHolder(ProprieteAdapter.VH holder, final int position) {

        final Propriete propriete = mProprietes.get(position);
//        final IProprieteItemDao.ProprieteItem proprieteItem = mProprieteItem.get(position);
        holder.adresseTV.setText(propriete.adresse);
//        holder.proprietaireTV.setText(proprieteItem.proprietaire);

        holder.editBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, BaseAddActivity.class);
                intent.putExtra(Constant.KEY_TYPE, Constant.PROPRIETE_TYPE);
                intent.putExtra(Constant.KEY_CODE_PROJECT, mCodeProject);
                Long codePropriete = new Long(propriete.getCodePropriete());
                Long codeRiverain = new Long(propriete.codeRiverain);
                intent.putExtra(Constant.KEY_CODE_PROPRIETE, codePropriete);
                intent.putExtra(Constant.KEY_CODE_RIVERAIN, codeRiverain);
                mActivity.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, BaseDetailActivity.class);
                Long codePropriete = new Long(propriete.getCodePropriete());
                intent.putExtra(Constant.KEY_CODE_PROPRIETE, codePropriete);
                intent.putExtra(Constant.KEY_TYPE, Constant.PROPRIETE_TYPE);
                mActivity.startActivity(intent);
            }
        });
        
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setMessage("Voulez-vous supprimer cet Propriete ?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deletePropriete(propriete.getCodePropriete());
                            }
                        })
                        .setNegativeButton("Non", null).show();
                
                return false;
            }
        });
    }

    @Override
    public ProprieteAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.propriete_item, parent, false);

        return new ProprieteAdapter.VH(view);
    }

    public void add(List<Propriete> proprieteItems) {
        mProprietes.addAll(proprieteItems);
    }

    public void clear() {
        mProprietes.clear();
    }

    @Override
    public int getItemCount() {
        return mProprietes.size();
    }
    
    public void deletePropriete(final int codePropriete) {
        (new AsyncTask<Void, Void, Integer>(){
            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                if (integer > 0) {
                    Toast.makeText(mActivity, "Propriete supprimee", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected Integer doInBackground(Void... voids) {
                return AcgtExpDatabase.getInstance().getIProprieteItemDao().delete(codePropriete);
            }
        }).execute();
    }

}
