package cd.acgt.acgtexp.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.entites.Litige;

/**
 * Created by Sugar on 7/18/2018
 */
public class LitigeAdapter extends RecyclerView.Adapter<LitigeAdapter.VH> {

    Activity mActivity;
    List<Litige> mLitiges = new ArrayList<>();
    String mDateFormat = "dd/MM/yyyy";
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(mDateFormat, Locale.FRANCE);

    static class VH extends RecyclerView.ViewHolder {
        TextView descriptionTV, dateEnregistrementTV, dateTraitementTV;

        public VH(View view) {
            super(view);
            descriptionTV = view.findViewById(R.id.description_tv);
            dateEnregistrementTV = view.findViewById(R.id.date_enregistrement_tv);
            dateTraitementTV = view.findViewById(R.id.date_traitement_tv);
        }
    }

    public LitigeAdapter(Activity activity) { this.mActivity = activity;}

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        final Litige litige = mLitiges.get(position);

        holder.descriptionTV.setText(litige.description);
        holder.dateEnregistrementTV.setText(mSimpleDateFormat.format(litige.dateEnregistrement));
        holder.dateTraitementTV.setText(mSimpleDateFormat.format(litige.dateTraitement));
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.litige_item, parent, false);

        return new LitigeAdapter.VH(view);
    }

    @Override
    public int getItemCount() {
        return mLitiges.size();
    }

    public void addAll(List<Litige> litiges) {
        mLitiges.addAll(litiges);
    }

    public void clear() {
        mLitiges.clear();
    }
}
