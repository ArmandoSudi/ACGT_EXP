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
import cd.acgt.acgtexp.entites.Paiement;

/**
 * Created by Sugar on 7/18/2018
 */
public class PaiementAdapter extends RecyclerView.Adapter<PaiementAdapter.VH> {

    Activity mActivity;
    List<Paiement> mPaiements = new ArrayList<>();
    String mDateFormat = "dd/MM/yyyy";
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(mDateFormat, Locale.FRANCE);

    static class VH extends RecyclerView.ViewHolder {
        TextView montantTV, datePaiementTV, typePaiementTV, referenceTV;

        public VH(View view) {
            super(view);
            montantTV = view.findViewById(R.id.montant_tv);
            datePaiementTV = view.findViewById(R.id.date_paiement_tv);
            typePaiementTV = view.findViewById(R.id.type_paiement_tv);
        }
    }

    public PaiementAdapter(Activity activity) { this.mActivity = activity;}

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        final Paiement paiement =  mPaiements.get(position);

        holder.montantTV.setText(paiement.montant + "");
        holder.typePaiementTV.setText(paiement.typePaiement);
        holder.datePaiementTV.setText(mSimpleDateFormat.format(paiement.datePaiement));
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.paiement_item, parent, false);

        return new PaiementAdapter.VH(view);
    }

    public void clear() {
        mPaiements.clear();
    }

    public void addAll(List<Paiement> paiements) {
        mPaiements.addAll(paiements);
    }

    @Override
    public int getItemCount() {
        return mPaiements.size();
    }
}
