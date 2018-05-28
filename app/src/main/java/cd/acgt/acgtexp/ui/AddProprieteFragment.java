package cd.acgt.acgtexp.ui;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import cd.acgt.acgtexp.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Propriete;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddProprieteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProprieteFragment extends Fragment {

    private static final String TAG = "AddProprieteFragment";

    EditText mAdresseET, mUrlPhotoOne, mUrlPhotoTwo, mUrlPhotoThree;
    Spinner mTypeProprieteSP;

    String mTypePropriete;
    String mCodeProjet;


    public AddProprieteFragment() {
        // Required empty public constructor
    }

    public static AddProprieteFragment newInstance(String codeProjet) {
        AddProprieteFragment fragment = new AddProprieteFragment();
        Bundle args = new Bundle();
        args.putString(Constant.KEY_CODE_PROJECT, codeProjet);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCodeProjet = getArguments().getString(Constant.KEY_CODE_PROJECT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_propriete, container, false);

        initScreen(view);

        Button saveBT = view.findViewById(R.id.save_bt);
        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePropriete();
            }
        });

        Button cancelBT = view.findViewById(R.id.cancel_bt);
        cancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }

    void initScreen(View view) {
        mAdresseET = view.findViewById(R.id.adresse_et);
        mUrlPhotoOne = view.findViewById(R.id.url_photo_one);
        mUrlPhotoTwo = view.findViewById(R.id.url_photo_two);
        mUrlPhotoThree = view.findViewById(R.id.url_photo_three);

        mTypeProprieteSP = view.findViewById(R.id.type_propriete_sp);
        mTypeProprieteSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTypePropriete = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    Propriete collectData() {
        String adresse = mAdresseET.getText().toString();
        String urlOne = "url one";
        String urlTwo = "url two";
        String urlThree = "url three";

        return new Propriete(adresse, mTypePropriete, urlOne, urlTwo, urlThree, 1, mCodeProjet );
    }

    public void savePropriete() {
        new SaveProprieteAsyncTask(collectData()).execute();
    }

    static class SaveProprieteAsyncTask extends AsyncTask<Void, Void, Void> {

        private static final String TAG = "SaveProprieteAsyncTask";
        Propriete mPropriete;

        public SaveProprieteAsyncTask(Propriete mPropriete) {
            this.mPropriete = mPropriete;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            long[] rowId = AcgtExpDatabase.getInstance().getIProprieteDao().insert(mPropriete);
            Log.e(TAG, "doInBackground: row ID: " + rowId[0]);
            return null;
        }
    }

}
