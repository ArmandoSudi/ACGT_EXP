package cd.acgt.acgtexp.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.entites.TypePropriete;

/**
 * Created by Sugar on 7/6/2018
 */
public class TypeProprieteAdapter extends RecyclerView.Adapter<TypeProprieteAdapter.BaseViewHolder> {

    Activity mActivity;
    List<TypePropriete> mTypeProprietes;
    private LayoutInflater mInflator;

    static class VH extends RecyclerView.ViewHolder {
        TextView longueurTV, largeurTV, nombreTV;

        public VH(View view) {
            super(view);
            longueurTV = view.findViewById(R.id.longueur_tv);
            largeurTV = view.findViewById(R.id.largeur_tv);
            nombreTV = view.findViewById(R.id.nombre_tv);
        }
    }

    public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bind(T object);
    }

    public TypeProprieteAdapter(Activity activity) {
        super();
        this.mActivity = activity;
        mTypeProprietes = new ArrayList<>();
        this.mInflator = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch(viewType){
            case 1:
                return new BatisHolder(mInflator.inflate(R.layout.batis_item, parent, false));
            case 2:
                return new MurHolder(mInflator.inflate(R.layout.mur_item, parent, false));
            default :
                return new BatisHolder(mInflator.inflate(R.layout.batis_item, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
       final TypePropriete typePropriete = mTypeProprietes.get(position);

       holder.bind(typePropriete);
    }

    public void add(List<TypePropriete> typeProprietes) {
        mTypeProprietes.addAll(typeProprietes);
    }

    @Override
    public int getItemCount() {
        return mTypeProprietes.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch(mTypeProprietes.get(position).type) {
            case "Batis":
                return 1;
            case "Mur de cloture":
                return 2;
            case "Mur de soutenement":
                return 2;
            case "Parcelle":
                return 1;
            default :
                return 1;
        }
    }

    /**
     * Holder for MurDeCloture and MurDeSoutenement.
     */
    public class MurHolder extends BaseViewHolder<TypePropriete>{
        private TextView longueurTV, largeurTV, typeTV, coutUnitaireTV, montantTV;

        public MurHolder(View itemView) {
            super(itemView);
            longueurTV = itemView.findViewById(R.id.longueur_tv);
            largeurTV = itemView.findViewById(R.id.largeur_tv);
            typeTV = itemView.findViewById(R.id.type_tv);
            montantTV = itemView.findViewById(R.id.montant_tv);
        }

        public void bind(TypePropriete typePropriete){
            longueurTV.setText(typePropriete.longueur + " m");
            largeurTV.setText(typePropriete.largeur + " m");
            typeTV.setText(typePropriete.type);
            montantTV.setText(typePropriete.montant + " $");
        }
    }

    /**
     * Holder for Batis
     */
    public class BatisHolder extends BaseViewHolder<TypePropriete> {
        private TextView typeTV, dateSignatureProtocoleTV, montantTV;

        public BatisHolder(View itemView) {
            super(itemView);
//            dateSignatureProtocoleTV = itemView.findViewById(R.id.date_signature_protocole_tv);
            typeTV = itemView.findViewById(R.id.type_tv);
            montantTV = itemView.findViewById(R.id.montant_tv);
        }

        public void bind(TypePropriete typePropriete){
//            dateSignatureProtocoleTV.setText(typePropriete.montant + " $");
            typeTV.setText(typePropriete.type);
            montantTV.setText(typePropriete.montant + " $");
        }
    }
}
