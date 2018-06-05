package cd.acgt.acgtexp.ui;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Riverain;
import cd.acgt.acgtexp.utils.Constant;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsRiverainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsRiverainFragment extends Fragment {

    private static final String TAG = "DetailsRiverainFragment";
    long mCodeRiverain;

    TextView mNomCompeltTV, mAdresseTV, mPhoneNumberTV, mEmailTV, mAutreInfoTV, mTypeTV, mTypePieceIdentite, mNumeroPieceIdentite, mNumeroRCCMTV, mNumeroImpotTV;
    ImageView mPieceIdentiteIV;

    public DetailsRiverainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment DetailsRiverainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsRiverainFragment newInstance(long codeRiverain) {
        DetailsRiverainFragment fragment = new DetailsRiverainFragment();
        Bundle args = new Bundle();
        args.putLong(Constant.KEY_CODE_RIVERAIN, codeRiverain);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCodeRiverain = getArguments().getLong(Constant.KEY_CODE_RIVERAIN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_proprietaire, container, false);

        initScreen(view);

        new GetRiverainAsynTask(mCodeRiverain).execute();

        return view;
    }

    public void initScreen(View view) {
        mNomCompeltTV = view.findViewById(R.id.nom_complet_tv);
        mAdresseTV = view.findViewById(R.id.adresse_tv);
        mPhoneNumberTV = view.findViewById(R.id.mobile_number_tv);
        mEmailTV = view.findViewById(R.id.email_tv);
        mAutreInfoTV = view.findViewById(R.id.autre_information_tv);
        mTypeTV = view.findViewById(R.id.type_tv);
        mTypePieceIdentite = view.findViewById(R.id.type_piece_identite_tv);
        mNumeroPieceIdentite = view.findViewById(R.id.numero_piece_identite_tv);
        mPieceIdentiteIV = view.findViewById(R.id.piece_identite_iv);
        mNumeroRCCMTV = view.findViewById(R.id.rccm_tv);
        mNumeroImpotTV = view.findViewById(R.id.impot_tv);
    }

    class GetRiverainAsynTask extends AsyncTask<Void, Void, Riverain> {

        long mCodeRiverain;

        public GetRiverainAsynTask(long mCodeRiverain) {
            this.mCodeRiverain = mCodeRiverain;
        }

        @Override
        protected void onPostExecute(Riverain riverain) {
            super.onPostExecute(riverain);
            if (riverain != null) {
                mNomCompeltTV.setText(riverain.getNomComplet());
                mAdresseTV.setText(riverain.getAdresse());
                mPhoneNumberTV.setText(riverain.getTelephone());
                mEmailTV.setText(riverain.getEmail());
                mAutreInfoTV.setText(riverain.getAutreInformation());
                mTypeTV.setText(riverain.getType());
                mTypePieceIdentite.setText(riverain.getPieceIdentite());
                mNumeroPieceIdentite.setText(riverain.getNumeroPieceIdentite());
                mPieceIdentiteIV.setImageResource(R.mipmap.ic_launcher_round);
                mNumeroRCCMTV.setText(riverain.getRccm());
                mNumeroImpotTV.setText(riverain.getNumeroImpot());
            }


        }

        @Override
        protected Riverain doInBackground(Void... voids) {
            Riverain riverain = AcgtExpDatabase.getInstance().getIRiverainDao().get(mCodeRiverain);
            return riverain;
        }
    }

}
