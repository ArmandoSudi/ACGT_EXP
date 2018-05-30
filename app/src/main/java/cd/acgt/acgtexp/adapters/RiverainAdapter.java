package cd.acgt.acgtexp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cd.acgt.acgtexp.utils.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.activites.BaseDetailActivity;
import cd.acgt.acgtexp.entites.Riverain;

/**
 * Created by Sugar on 5/21/2018
 */
public class RiverainAdapter extends RecyclerView.Adapter<RiverainAdapter.VH> {

    Activity mActivity;
    List<Riverain> mRiverains = new ArrayList<>();

    public static class VH extends RecyclerView.ViewHolder {
        TextView nomTV, typeTV;

        public VH(View view) {
            super(view);
            nomTV = view.findViewById(R.id.nom_tv);
            typeTV = view.findViewById(R.id.type_tv);
        }
    }

    public RiverainAdapter(Activity activity)  {
        this.mActivity = activity;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Riverain riverain = mRiverains.get(position);

        holder.nomTV.setText(riverain.getNomComplet());
        holder.typeTV.setText(riverain.getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, BaseDetailActivity.class);
                intent.putExtra(Constant.KEY_TYPE, Constant.RIVERAIN_TYPE);
                mActivity.startActivity(intent);
            }
        });

    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.riverain_item, parent, false);

        return new VH(view);
    }

    @Override
    public int getItemCount() {
        return mRiverains.size();
    }

    public void addAll(List<Riverain> riverains) {
        mRiverains.addAll(riverains);
    }

    public void clear() {
        mRiverains.clear();
    }
}
