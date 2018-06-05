package cd.acgt.acgtexp.ui;


import android.Manifest;
import android.app.Activity;
import cd.acgt.acgtexp.activites.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.pix.Pix;

import java.util.ArrayList;
import java.util.List;

import cd.acgt.acgtexp.entites.Riverain;
import cd.acgt.acgtexp.utils.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.adapters.SelectedPhotoAdapter;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Propriete;
import cd.acgt.acgtexp.utils.GPSTracker;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddProprieteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProprieteFragment extends Fragment {

    private static final String TAG = "AddProprieteFragment";
    Activity mActivity;

    EditText mAdresseET, mLatitudeET, mLongitudeET;
    TextInputLayout mAdresseTI;
    TextView mRiverainNomTV;
    ImageButton mGPSBT;
    Spinner mTypeProprieteSP;
    RecyclerView selectedPhotoRv;

    SelectedPhotoAdapter mSelectedPhotoAdapter;
    private GPSAsyncTask gpsAsyncTask;

    String mTypePropriete;
    String mCodeProjet;
    long mRiverainId;

    List<String> mPhotoPaths = new ArrayList<>();

    private int REQUEST_CODE = 123;

    public AddProprieteFragment() {
        // Required empty public constructor
    }

    public static AddProprieteFragment newInstance(String codeProjet, long riverainId) {
        AddProprieteFragment fragment = new AddProprieteFragment();
        Bundle args = new Bundle();
        args.putString(Constant.KEY_CODE_PROJECT, codeProjet);
        args.putLong(Constant.KEY_CODE_RIVERAIN, riverainId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCodeProjet = getArguments().getString(Constant.KEY_CODE_PROJECT);
            mRiverainId = getArguments().getLong(Constant.KEY_CODE_RIVERAIN);
            new GetRiverainAsyncTask(mRiverainId).execute();
        }

        mActivity = getActivity();
        mSelectedPhotoAdapter = new SelectedPhotoAdapter(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_propriete, container, false);

        initScreen(view);

        askExternalStoragePermissionAtRunTime();

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

        ImageButton pickImages = view.findViewById(R.id.pick_image_bt);
        pickImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pix.start(getActivity(), REQUEST_CODE, 3);
            }
        });
        return view;
    }

    void initScreen(View view) {
        mAdresseET = view.findViewById(R.id.adresse_et);
        mAdresseTI = view.findViewById(R.id.adresse_ti);
        mLatitudeET = view.findViewById(R.id.latitude_et);
        mLongitudeET = view.findViewById(R.id.longitude_et);
        mGPSBT = view.findViewById(R.id.find_gps_bt);
        mGPSBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsAsyncTask = new GPSAsyncTask(getActivity());
                gpsAsyncTask.execute();
            }
        });
        mRiverainNomTV = view.findViewById(R.id.riverain_nom_tv);

        selectedPhotoRv = view.findViewById(R.id.selected_photo_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation());
        selectedPhotoRv.setHasFixedSize(true);
        selectedPhotoRv.setLayoutManager(linearLayoutManager);
        selectedPhotoRv.setAdapter(mSelectedPhotoAdapter);

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

        mAdresseTI.setError("");
        boolean isValid = true;

        String adresse = mAdresseET.getText().toString();

        //TODO Select only url when its different from null, otherwise store default url
        String urlOne = mSelectedPhotoAdapter.getImagePaths().get(0);
        String urlTwo = mSelectedPhotoAdapter.getImagePaths().get(0);
        String urlThree = mSelectedPhotoAdapter.getImagePaths().get(0);

        if (adresse.equals("")) {
            mAdresseTI.setError("L'adresee ne peut pas etre vide");
            isValid = false;
        }

        if (mSelectedPhotoAdapter.getImagePaths().size() == 0) {
            isValid = false;
        }

        if (isValid){ return new Propriete(mTypePropriete, adresse, urlOne, urlTwo, urlThree, 1, mCodeProjet );
        } else { return null; }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            mPhotoPaths = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            Log.e(TAG, "onActivityResult: " + mPhotoPaths.size());

            if(mPhotoPaths.size() > 0) {
                selectedPhotoRv.setVisibility(View.VISIBLE);
                mSelectedPhotoAdapter.addPhotoPaths(mPhotoPaths);
                mSelectedPhotoAdapter.notifyDataSetChanged();
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

    public void savePropriete() {
        if(collectData() == null) {
            Toast.makeText(mActivity, "Veuillez completer tous les champs", Toast.LENGTH_SHORT).show();
        } else if (collectData() instanceof Propriete ) {
            new SaveProprieteAsyncTask(collectData()).execute();
        }
    }

    class SaveProprieteAsyncTask extends AsyncTask<Void, Void, long[]> {

        private static final String TAG = "SaveProprieteAsyncTask";
        Propriete mPropriete;

        public SaveProprieteAsyncTask(Propriete mPropriete) {
            this.mPropriete = mPropriete;
        }

        @Override
        protected long[] doInBackground(Void... voids) {
            long[] rowId = AcgtExpDatabase.getInstance().getIProprieteDao().insert(mPropriete);
            Log.e(TAG, "doInBackground: row ID: " + rowId[0]);
            return rowId;
        }

        @Override
        protected void onPostExecute(long[] rowId) {
            super.onPostExecute(rowId);

            if( rowId[0] > 0) {
                Toast.makeText(mActivity, "Propriete ajoute avec succes", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mActivity, ListActivity.class);
                intent.putExtra(Constant.KEY_CODE_PROJECT, mCodeProjet);
                mActivity.startActivity(intent);
                mActivity.finish();
            }
        }
    }

    class GetRiverainAsyncTask extends AsyncTask<Void, Void, Riverain> {
        long codeRiverain;

        public GetRiverainAsyncTask(long codeRiverain) {
            this.codeRiverain = codeRiverain;
        }

        @Override
        protected Riverain doInBackground(Void... voids) {
            Riverain riverain = AcgtExpDatabase.getInstance().getIRiverainDao().get(codeRiverain);
            return riverain;
        }

        @Override
        protected void onPostExecute(Riverain riverain) {
            super.onPostExecute(riverain);
            if (mRiverainNomTV != null ) {
                mRiverainNomTV.setText(riverain.getNomComplet());
            }
        }
    }

    class GPSAsyncTask extends  AsyncTask<Void, Object, Object>{

        private Context context;
        private ProgressDialog progressDialog;
        private double latitude;
        private double longitude;

        private GPSTracker gps;

        public GPSAsyncTask(Context context){
            this.context = context;
            this.gps = new GPSTracker(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(
                    context,
                    "Rechercher des coordonnées GPS",
                    "Veuillez patientez pendant la recherche...",
                    true,
                    true,
                    new DialogInterface.OnCancelListener(){
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            cancel(true);
                        }
                    }
            );

            if(!gps.canGetLocation()){
                gps.showSettingsAlert();
            }
        }

        @Override
        protected Object doInBackground(Void... voids) {
            if (gps.canGetLocation()) {

                do{
                    try {
                        Thread.sleep(1000 * 5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    gps.getLocation();

                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();

                    publishProgress("Longitude: " + longitude + " et Latitude: " + latitude);
                }while (!isCancelled());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if(progressDialog != null && progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            mLatitudeET.setText(latitude + "");
            mLongitudeET.setText(longitude + "");
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);

            progressDialog.setMessage(String.valueOf(values[0]));
        }

        @Override
        protected void onCancelled(Object o) {
            super.onCancelled(o);

            if(progressDialog != null && progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            mLatitudeET.setText(latitude + "");
            mLongitudeET.setText(longitude + "");
        }
    }

}
