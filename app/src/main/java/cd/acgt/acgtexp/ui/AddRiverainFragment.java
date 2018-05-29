package cd.acgt.acgtexp.ui;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.fxn.pix.Pix;

import java.util.ArrayList;

import cd.acgt.acgtexp.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Riverain;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRiverainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRiverainFragment extends Fragment {

    private static final String TAG = "AddRiverainFragment";

    EditText mNomCompletET, mAdresseET, mNumeroTelephoneET, mEmailET, mAutreInfoEt, mRepresentantET, mNumeroPieceIdentiteEt, mNumeroRCCMET, mNumeroImpotET;
    Spinner mTypeRiverainSP, mTypePieceIdentiteSP;
    Button saveBT, cancelBT, capturePieceIdentiteBT;
    ImageView mPieceIdentiteIV;
    String mCodeProjet, mTypeRiverain, mTypePieceIdentite;

    String mPieceIdentiteImagePaths;

    private int REQUEST_CODE = 123;

    public AddRiverainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *@param codeProjet The code of the Project for which we are adding the Riverain
     * @return A new instance of fragment AddRiverainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRiverainFragment newInstance(String codeProjet) {
        AddRiverainFragment fragment = new AddRiverainFragment();
        Bundle args = new Bundle();
        args.putString(Constant.KEY_CODE_PROJECT, codeProjet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCodeProjet = getArguments().getString(Constant.KEY_CODE_PROJECT);
            Toast.makeText(getActivity(), "" + mCodeProjet, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_riverain, container, false);

        initScreen(view);

        askExternalStoragePermissionAtRunTime();

        return view;
    }

    public void initScreen(View view) {
        mNomCompletET = view.findViewById(R.id.nom_complet_et);
        mAdresseET =  view.findViewById(R.id.adresse_et);
        mNumeroTelephoneET = view.findViewById(R.id.numero_telephone_et);
        mEmailET = view.findViewById(R.id.email_et);
        mAutreInfoEt = view.findViewById(R.id.autres_informations_et);
        mRepresentantET = view.findViewById(R.id.representant_et);
        mNumeroPieceIdentiteEt = view.findViewById(R.id.numero_piece_identite_et);
        mNumeroRCCMET = view.findViewById(R.id.numero_rccm_et);
        mNumeroImpotET = view.findViewById(R.id.numero_impot_et);

        mPieceIdentiteIV = view.findViewById(R.id.piece_identite_iv);

        mTypeRiverainSP = view.findViewById(R.id.type_riverrain_sp);
        mTypePieceIdentiteSP = view.findViewById(R.id.piece_identite_sp);

        mTypeRiverainSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTypeRiverain = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mTypePieceIdentiteSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTypePieceIdentite = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveBT = view.findViewById(R.id.save_bt);
        cancelBT = view.findViewById(R.id.cancel_bt);
        capturePieceIdentiteBT = view.findViewById(R.id.capture_piece_identite_bt);

        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRiverain();
            }
        });
        cancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        capturePieceIdentiteBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pix.start(getActivity(), REQUEST_CODE);
            }
        });
    }

    public Riverain collectData() {
        String nomComplet = mNomCompletET.getText().toString();
        String addresse = mAdresseET.getText().toString();
        String numeroTelephone = mNumeroTelephoneET.getText().toString();
        String email  = mEmailET.getText().toString();
        String autreInfo = mAutreInfoEt.getText().toString();
        String representant = mRepresentantET.getText().toString();
        String numeroPieceIdentite = mNumeroPieceIdentiteEt.getText().toString();
        String numeroRCCM = mNumeroRCCMET.getText().toString();
        String numeroImpo = mNumeroImpotET.getText().toString();

        //TODO Implement method to retrieve piece identite URL
        Riverain riverain = new Riverain(nomComplet, addresse, numeroTelephone, email, autreInfo, mTypeRiverain, representant, mTypePieceIdentite, numeroPieceIdentite, mPieceIdentiteImagePaths, numeroRCCM, numeroImpo, mCodeProjet );

        return riverain;

    }

    public void saveRiverain() {
        new SaveRiverainAsyncTask(collectData()).execute();
    }

    protected void askExternalStoragePermissionAtRunTime() {
        // Enable if permission granted
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
        }
        // Else ask for permission
        else {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                    { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE ) {
            mPieceIdentiteImagePaths = data.getStringArrayListExtra(Pix.IMAGE_RESULTS).get(0);
            Log.e(TAG, "onActivityResult: " + mPieceIdentiteImagePaths );

            if(mPieceIdentiteImagePaths != null) {
                mPieceIdentiteIV.setVisibility(View.VISIBLE);
                Bitmap bitmap = BitmapFactory.decodeFile(mPieceIdentiteImagePaths);
                mPieceIdentiteIV.setImageBitmap(bitmap);
            }
        }
    }

    static class SaveRiverainAsyncTask extends AsyncTask<Void, Void, Void> {

        private static final String TAG = "saveRiverainAsyncTask";
        Riverain riverain;

        public SaveRiverainAsyncTask(Riverain riverain) {
            this.riverain = riverain;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            long[] rowId = AcgtExpDatabase.getInstance().getIRiverainDao().insert(riverain);
            Log.e(TAG, "doInBackground: row ID: " + rowId[0] );
            return null;
        }
    }
}
