package cd.acgt.acgtexp.ui;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
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
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.pix.Pix;
import com.squareup.picasso.Picasso;

import cd.acgt.acgtexp.activites.BaseAddActivity;
import cd.acgt.acgtexp.adapters.SelectedPhotoAdapter;
import cd.acgt.acgtexp.entites.Projet;
import cd.acgt.acgtexp.utils.Constant;
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
    private Activity mActivity;

    EditText mNomCompletET, mAdresseET, mNumeroTelephoneET, mEmailET, mAutreInfoEt, mRepresentantET, mNumeroPieceIdentiteEt, mNumeroRCCMET, mNumeroImpotET;
    TextInputLayout mNomCompletTI, mAdresseTI, mNumeroTelephoneTI, mAutreInfoTI, mEmailTI, mRepresentantTI, mNumeroPieceIdentiteTI, mNumeroRCCMTI, mNumeroImpoTI;
    TextView nomProjetTV;
    Spinner mTypeRiverainSP, mTypePieceIdentiteSP;
    Button saveBT, cancelBT, capturePieceIdentiteBT, addProprieteBT;
    ImageView mPieceIdentiteIV;
    String mCodeProjet, mTypeRiverain, mTypePieceIdentite;

    String mPieceIdentiteImagePaths;

    Riverain mRiverainToUpdate;

    private int REQUEST_CODE = 123;
    boolean isUpdating = false;
    long mCodeRiverain = 0;

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
    public static AddRiverainFragment newInstance(String codeProjet, long codeRiverain) {
        AddRiverainFragment fragment = new AddRiverainFragment();
        Bundle args = new Bundle();
        args.putString(Constant.KEY_CODE_PROJECT, codeProjet);
        args.putLong(Constant.KEY_CODE_RIVERAIN, codeRiverain);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCodeProjet = getArguments().getString(Constant.KEY_CODE_PROJECT);
        mCodeRiverain = getArguments().getLong(Constant.KEY_CODE_RIVERAIN);

        if (mCodeProjet != null) {
            mCodeProjet = getArguments().getString(Constant.KEY_CODE_PROJECT);
            new GetProjetAsynTask(mCodeProjet).execute();
        }
        if (mCodeRiverain != 0L) {
            isUpdating = true;
            new GetRiverain(mCodeRiverain).execute();
        }
        mActivity = getActivity();
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
        nomProjetTV = view.findViewById(R.id.nom_projet_tv);
        mNomCompletET = view.findViewById(R.id.nom_complet_et);
        mAdresseET =  view.findViewById(R.id.adresse_et);
        mNumeroTelephoneET = view.findViewById(R.id.numero_telephone_et);
        mEmailET = view.findViewById(R.id.email_et);
        mAutreInfoEt = view.findViewById(R.id.autres_informations_et);
        mRepresentantET = view.findViewById(R.id.representant_et);
        mNumeroPieceIdentiteEt = view.findViewById(R.id.numero_piece_identite_et);
        mNumeroRCCMET = view.findViewById(R.id.numero_rccm_et);
        mNumeroImpotET = view.findViewById(R.id.numero_impot_et);

        mNomCompletTI = view.findViewById(R.id.nomc_complet_ti);
        mAdresseTI = view.findViewById(R.id.adresse_ti);
        mNumeroTelephoneTI = view.findViewById(R.id.numero_telephone_ti);
        mEmailTI = view.findViewById(R.id.email_ti);
        mAutreInfoTI = view.findViewById(R.id.autres_informations_ti);
        mRepresentantTI = view.findViewById(R.id.representant_ti);
        mNumeroPieceIdentiteTI = view.findViewById(R.id.numero_piece_identite_ti);
        mNumeroRCCMTI = view.findViewById(R.id.numero_rccm_ti);
        mNumeroImpoTI = view.findViewById(R.id.numero_impot_ti);

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
        if (isUpdating) {
            saveBT.setText("Mettre a jour");
        }
        cancelBT = view.findViewById(R.id.cancel_bt);
        addProprieteBT = view.findViewById(R.id.add_propriete_bt);
        capturePieceIdentiteBT = view.findViewById(R.id.capture_piece_identite_bt);

        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectData();
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

    void initData(Riverain riverain){
        mRiverainToUpdate = riverain;
        mNomCompletET.setText(riverain.getNomComplet());
        mAdresseET.setText(riverain.getAdresse());
        mNumeroTelephoneET.setText(riverain.getTelephone());
        mEmailET.setText(riverain.getEmail());
        mAutreInfoEt.setText(riverain.getAutreInformation());
        mRepresentantET.setText(riverain.getRepresentant());
        mNumeroPieceIdentiteEt.setText(riverain.getNumeroPieceIdentite());
        mNumeroRCCMET.setText(riverain.getRccm());
        mNumeroImpotET.setText(riverain.getNumeroImpot());
    }

    public void collectData() {
        String nomComplet = mNomCompletET.getText().toString();
        String addresse = mAdresseET.getText().toString();
        String numeroTelephone = mNumeroTelephoneET.getText().toString();
        String email  = mEmailET.getText().toString();
        String autreInfo = mAutreInfoEt.getText().toString();
        String representant = mRepresentantET.getText().toString();
        String numeroPieceIdentite = mNumeroPieceIdentiteEt.getText().toString();
        String numeroRCCM = mNumeroRCCMET.getText().toString();
        String numeroImpo = mNumeroImpotET.getText().toString();

        boolean isValid = true;

        if (nomComplet.equals("")){
            mNomCompletTI.setError("Le nom complet ne peut pas etre vide");
            isValid = false;
        }
        if (addresse.equals("")) {
            mAdresseTI.setError("L'adresse ne peut pas etre vide");
            isValid = false;
        }
        if (numeroTelephone.equals("")) {
            mNumeroTelephoneTI.setError("Le numero de telephone ne peut pas etre vide");
            isValid = false;
        }
        if (email.equals("")) {
            mEmailTI.setError("Email ne peut pas etre vide");
            isValid = false;
        }
        if (autreInfo.equals("")) {
            mAutreInfoTI.setError("Autre info ne peut pas etre vide");
            isValid = false;
        }
        if (numeroImpo.equals("")) {
            mNumeroPieceIdentiteTI.setError("Le numero de la piece d'identite ne peut pas etre vide");
            isValid = false;
        }
        if (numeroRCCM.equals("")) {
            mNumeroRCCMTI.setError("Le numero RCCM ne peut pas etre vide");
            isValid = false;
        }
        if (numeroImpo.equals("")) {
            mNumeroImpoTI.setError("Le numero de l'impot ne peut pas etre vide");
            isValid = false;
        }

        if (isValid) {
            //TODO Implement method to retrieve piece identite URL
            Riverain riverain = new Riverain(nomComplet, addresse, numeroTelephone, email, autreInfo, mTypeRiverain, representant, mTypePieceIdentite, numeroPieceIdentite, mPieceIdentiteImagePaths, numeroRCCM, numeroImpo, mCodeProjet);

            if (isUpdating) {
                mRiverainToUpdate.setNomComplet(nomComplet);
                mRiverainToUpdate.setAdresse(addresse);
                mRiverainToUpdate.setTelephone(numeroTelephone);
                mRiverainToUpdate.setEmail(email);
                mRiverainToUpdate.setAutreInformation(autreInfo);
                mRiverainToUpdate.setRepresentant(representant);
                mRiverainToUpdate.setNumeroPieceIdentite(numeroPieceIdentite);
                mRiverainToUpdate.setRccm(numeroRCCM);
                mRiverainToUpdate.setNumeroImpot(numeroImpo);
                if (mPieceIdentiteImagePaths != null) {
                    mRiverainToUpdate.setUrlPieceIdentite(mPieceIdentiteImagePaths);
                }
                if (mTypeRiverain != null) {
                    mRiverainToUpdate.setType(mTypeRiverain);
                }
                if (mTypePieceIdentite != null) {
                    mRiverainToUpdate.setPieceIdentite(mTypePieceIdentite);
                }

                new UpdateRiverainAsyncTask(mRiverainToUpdate).execute();
            } else {
                new SaveRiverainAsyncTask(riverain).execute();
            }
        }
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
//                Bitmap bitmap = BitmapFactory.decodeFile(mPieceIdentiteImagePaths);
                Picasso.get().load("file:" + mPieceIdentiteImagePaths).into(mPieceIdentiteIV);
//                mPieceIdentiteIV.setImageBitmap(bitmap);
            }
        }
    }

    class GetProjetAsynTask extends AsyncTask<Void, Void, Projet> {

        String codeProjet;

        public GetProjetAsynTask(String codeProjet) {
            this.codeProjet = codeProjet;
        }

        @Override
        protected Projet doInBackground(Void... voids) {
            return AcgtExpDatabase.getInstance().getIProjetDao().get(codeProjet);
        }

        @Override
        protected void onPostExecute(Projet projet) {
            super.onPostExecute(projet);

            nomProjetTV.setText(projet.getDesignation());
        }
    }

    class SaveRiverainAsyncTask extends AsyncTask<Void, Void, long[]> {

        private static final String TAG = "saveRiverainAsyncTask";
        Riverain riverain;

        public SaveRiverainAsyncTask(Riverain riverain) {
            this.riverain = riverain;
        }

        @Override
        protected long[] doInBackground(Void... voids) {
            long[] rowId = AcgtExpDatabase.getInstance().getIRiverainDao().insert(riverain);

            Log.e(TAG, "doInBackground: row ID: " + rowId[0] );
            return rowId;
        }

        @Override
        protected void onPostExecute(final long[] rowId) {
            super.onPostExecute(rowId);
            if (rowId[0] > 0) {
                Toast.makeText(mActivity, "Riverrain enregistre", Toast.LENGTH_SHORT).show();
                saveBT.setVisibility(View.GONE);
                cancelBT.setVisibility(View.GONE );
                addProprieteBT.setVisibility(View.VISIBLE);
                addProprieteBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivity, BaseAddActivity.class);
                        intent.putExtra(Constant.KEY_TYPE, Constant.PROPRIETE_TYPE);
                        intent.putExtra(Constant.KEY_CODE_PROJECT, mCodeProjet);
                        intent.putExtra(Constant.KEY_CODE_RIVERAIN, rowId[0]);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    class UpdateRiverainAsyncTask extends AsyncTask<Void, Void, Integer> {
        Riverain riverain;

        public UpdateRiverainAsyncTask(Riverain riverain) {
            this.riverain = riverain;
        }

        @Override
        protected void onPostExecute(Integer i) {
            super.onPostExecute(i);

            if (i > 0 ) {
                Toast.makeText(mActivity, "Riverain mis a jour", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            int updatedRows = AcgtExpDatabase.getInstance().getIRiverainDao().update(this.riverain);
            return updatedRows;
        }
    }

    class GetRiverain extends AsyncTask<Void, Void, Riverain> {
        long codeRiverain;

        public GetRiverain(long codeRiverain) {
            this.codeRiverain = codeRiverain;
        }

        @Override
        protected void onPostExecute(Riverain riverain) {
            super.onPostExecute(riverain);

            initData(riverain);
        }

        @Override
        protected Riverain doInBackground(Void... voids) {
            Riverain riverain = AcgtExpDatabase.getInstance().getIRiverainDao().get(codeRiverain);
            return riverain;
        }
    }
}
