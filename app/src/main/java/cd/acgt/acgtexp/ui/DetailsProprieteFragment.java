package cd.acgt.acgtexp.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.activites.MapsActivity;
import cd.acgt.acgtexp.adapters.LitigeAdapter;
import cd.acgt.acgtexp.adapters.PaiementAdapter;
import cd.acgt.acgtexp.adapters.SelectedPhotoAdapter;
import cd.acgt.acgtexp.adapters.TypeProprieteAdapter;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Litige;
import cd.acgt.acgtexp.entites.Paiement;
import cd.acgt.acgtexp.entites.Propriete;
import cd.acgt.acgtexp.entites.TypePropriete;
import cd.acgt.acgtexp.utils.Constant;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsProprieteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsProprieteFragment extends Fragment {

    RecyclerView mImagesRV, mTypeProprieteRV, mPaiementRV, mLitigeRV;
    TextView mAdresseTV, mTypeTV;
    Button mMapBT;

    SelectedPhotoAdapter mPhotoAdapter;
    TypeProprieteAdapter mTypeProprieteAdapter;
    LitigeAdapter mLitigeAdapter;
    PaiementAdapter mPaiementAdapter;

    long mCodePropriete;
    Propriete mPropriete;
    Activity mActivity;

    public DetailsProprieteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DetailsProprieteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsProprieteFragment newInstance(long codePropriete) {
        DetailsProprieteFragment fragment = new DetailsProprieteFragment();
        Bundle args = new Bundle();
        args.putLong(Constant.KEY_CODE_PROPRIETE, codePropriete);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mCodePropriete = getArguments().getLong(Constant.KEY_CODE_PROPRIETE);
        }
        mActivity = getActivity();
        mPhotoAdapter = new SelectedPhotoAdapter(mActivity);
        mTypeProprieteAdapter = new TypeProprieteAdapter(mActivity);
        mLitigeAdapter = new LitigeAdapter(mActivity);
        mPaiementAdapter = new PaiementAdapter(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_propriete, container, false);

        initView(view);

        mMapBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, MapsActivity.class);
                intent.putExtra(Constant.KEY_LATITUDE, mPropriete.getLatitude());
                intent.putExtra(Constant.KEY_LONGITUDE, mPropriete.getLongitude());
                startActivity(intent);
            }
        });

        new GetProprieteAsyncTask(mCodePropriete).execute();

        new GetTypeProprieteAsycn(mCodePropriete).execute();

        return view;
    }

    public void initView(View view) {
        mImagesRV = view.findViewById(R.id.images_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mImagesRV.setHasFixedSize(true);
        mImagesRV.setLayoutManager(linearLayoutManager);
        mImagesRV.setAdapter(mPhotoAdapter);

        mTypeProprieteRV = view.findViewById(R.id.type_propriete_rv);
        mTypeProprieteRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mTypeProprieteRV.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayoutManager.VERTICAL));
        mTypeProprieteRV.setHasFixedSize(true);
        mTypeProprieteRV.setAdapter(mTypeProprieteAdapter);

        mPaiementRV = view.findViewById(R.id.paiement_rv);
        mPaiementRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mPaiementRV.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayoutManager.VERTICAL));
        mPaiementRV.setHasFixedSize(true);
        mPaiementRV.setAdapter(mPaiementAdapter);

        mLitigeRV = view.findViewById(R.id.litige_rv);
        mLitigeRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mLitigeRV.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayoutManager.VERTICAL));
        mLitigeRV.setHasFixedSize(true);
        mLitigeRV.setAdapter(mLitigeAdapter);

        mAdresseTV = view.findViewById(R.id.adresse_tv);
        mTypeTV = view.findViewById(R.id.type_tv);
        mMapBT = view.findViewById(R.id.map_bt);
    }

    class GetProprieteAsyncTask extends AsyncTask<Void, Void, Propriete> {

        long mCodePropriete;

        public GetProprieteAsyncTask(long mCodePropriete) {
            this.mCodePropriete = mCodePropriete;
        }

        @Override
        protected void onPostExecute(Propriete propriete) {
            super.onPostExecute(propriete);
            mPropriete = propriete;
            mAdresseTV.setText(propriete.getAdresse());
//            mTypeTV.setText(propriete.getType());
//            if (propriete.getUrlPhoto1() != null) mPhotoAdapter.addPhotUrl(propriete.getUrlPhoto1());
//            if (propriete.getUrlPhoto2() != null) mPhotoAdapter.addPhotUrl(propriete.getUrlPhoto2());
//            if (propriete.getUrlPhoto3() != null) mPhotoAdapter.addPhotUrl(propriete.getUrlPhoto3());

            mPhotoAdapter.notifyDataSetChanged();
        }

        @Override
        protected Propriete doInBackground(Void... voids) {
            Propriete propriete = AcgtExpDatabase.getInstance().getIProprieteDao().get(mCodePropriete);
            return propriete;
        }
    }

    class GetTypeProprieteAsycn extends AsyncTask<Void, Void, List<TypePropriete>> {
        private static final String TAG = "GetTypeProprieteAsycn";
        long codePropriete;
        List<Litige> litiges = new ArrayList<>();
        List<Paiement> paiements = new ArrayList<>();

        public GetTypeProprieteAsycn(long codePropriete) {
            this.codePropriete = codePropriete;
        }

        @Override
        protected void onPostExecute(List<TypePropriete> typeProprietes) {
            super.onPostExecute(typeProprietes);

            Log.e(TAG, "onPostExecute: " + typeProprietes.size() );
            mTypeProprieteAdapter.add(typeProprietes);
            mTypeProprieteAdapter.notifyDataSetChanged();

            mLitigeAdapter.addAll(litiges);
            mPaiementAdapter.addAll(paiements);

            mPaiementAdapter.notifyDataSetChanged();
            mLitigeAdapter.notifyDataSetChanged();
        }

        @Override
        protected List<TypePropriete> doInBackground(Void... voids) {
            litiges = AcgtExpDatabase.getInstance().getILitigeDao().get();
            paiements = AcgtExpDatabase.getInstance().getIPaiementDao().get();
            return AcgtExpDatabase.getInstance().getITypeProprieteDao().get(codePropriete);
        }
    }

}
