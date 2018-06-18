package cd.acgt.acgtexp.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Propriete;
import cd.acgt.acgtexp.utils.Constant;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsProprieteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsProprieteFragment extends Fragment {

    RecyclerView mImagesRV;
    TextView mAdresseTV, mTypeTV;
    Button mMapBT;

    long mCodePropriete;


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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_propriete, container, false);

        initView(view);

        mMapBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
                startActivity(intent);
            }
        });

        new GetProprieteAsyncTask(mCodePropriete).execute();

        return view;
    }


    public void initView(View view) {
        mImagesRV = view.findViewById(R.id.images_rv);
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
        }

        @Override
        protected Propriete doInBackground(Void... voids) {
            Propriete propriete = AcgtExpDatabase.getInstance().getIProprieteDao().get(mCodePropriete);
            return propriete;
        }
    }

}
