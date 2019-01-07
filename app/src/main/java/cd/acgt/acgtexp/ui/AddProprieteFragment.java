package cd.acgt.acgtexp.ui;


import android.Manifest;
import android.app.Activity;
import cd.acgt.acgtexp.activites.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cd.acgt.acgtexp.entites.Riverain;
import cd.acgt.acgtexp.utils.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.adapters.SelectedPhotoAdapter;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Propriete;
import cd.acgt.acgtexp.utils.FIleStorage;
import cd.acgt.acgtexp.utils.GPSTracker;

import static android.content.ContentValues.TAG;

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
    ImageButton mGPSBT, mPickImageBT;
    Spinner mTypeProprieteSP;
    RecyclerView selectedPhotoRv;

    SelectedPhotoAdapter mSelectedPhotoAdapter;
    private GPSAsyncTask gpsAsyncTask;

    String mTypePropriete, mCodeProjet, mCurrentPhotoPath;
    long mRiverainId, mProprieteId;
    boolean isUpdating;
    Propriete mProprieteToUpdate;

    List<String> mPhotoPaths = new ArrayList<>();

    private int REQUEST_CODE = 123;

    public AddProprieteFragment() {
        // Required empty public constructor
    }

    public static AddProprieteFragment newInstance(String codeProjet, long riverainId, long proprieteID) {
        AddProprieteFragment fragment = new AddProprieteFragment();
        Bundle args = new Bundle();
        args.putString(Constant.KEY_CODE_PROJECT, codeProjet);
        args.putLong(Constant.KEY_CODE_RIVERAIN, riverainId);
        args.putLong(Constant.KEY_CODE_PROPRIETE, proprieteID);
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

        mProprieteId = getArguments().getLong(Constant.KEY_CODE_PROPRIETE);

        if (getArguments() != null) {
            mCodeProjet = getArguments().getString(Constant.KEY_CODE_PROJECT);
            mRiverainId = getArguments().getLong(Constant.KEY_CODE_RIVERAIN);

            if (mRiverainId != 0) new GetRiverainAsyncTask(mRiverainId).execute();
        }

        if (mProprieteId != 0L) {
            isUpdating = true;
//            new GetProprieteAsyncTask(mProprieteId).execute();
        }

        mActivity = getActivity();
        Toast.makeText(mActivity, "code prorietaire " + mRiverainId, Toast.LENGTH_SHORT).show();
        mSelectedPhotoAdapter = new SelectedPhotoAdapter(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_propriete, container, false);

        initScreen(view);

        askExternalStoragePermissionAtRunTime();

        mPickImageBT = view.findViewById(R.id.pick_image_bt);
        mPickImageBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(Constant.REQUEST_TAKE_PHOTO_PROPRIETE);
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

        Button saveBT = view.findViewById(R.id.save_bt);
        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectData();
            }
        });

        Button cancelBT = view.findViewById(R.id.cancel_bt);
        cancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        if (isUpdating) {
            saveBT.setText("Mettre a jour");
        }
    }

    void collectData() {

        String urlOne = null , urlTwo = null , urlThree = null;

        mAdresseTI.setError("");
        boolean isValid = true;

        String adresse = mAdresseET.getText().toString();

        //TODO Select only url when its different from null, otherwise store default url
        int size = mSelectedPhotoAdapter.getImagePaths().size() - 1;
        if (size >= 0) urlOne = mSelectedPhotoAdapter.getImagePaths().get(size--);
        if (size >= 0) urlTwo = mSelectedPhotoAdapter.getImagePaths().get(size--);
        if (size >= 0) urlThree = mSelectedPhotoAdapter.getImagePaths().get(size--);

        double latitude = Double.parseDouble(mLatitudeET.getText().toString());
        double longitude = Double.parseDouble(mLongitudeET.getText().toString());

        if (adresse.equals("")) {
            mAdresseTI.setError("L'adresee ne peut pas etre vide");
            isValid = false;
        }

        if (mSelectedPhotoAdapter.getImagePaths().size() == 0 && !isUpdating) {
            isValid = false;
        }

//        if (latitude == 0.0 || longitude == 0.0 ) {
//            isValid = false;
//        }

        if (isValid){
            //TODO correct the constructor for propriete
//            Propriete propriete = new Propriete(mTypePropriete, adresse, urlOne, urlTwo, urlThree, 1, mCodeProjet, latitude, longitude);

            if (isUpdating) {

                mProprieteToUpdate.setAdresse(adresse);
                String urlImages = "";
                if (urlOne != null) urlImages = urlOne;
                if (urlTwo != null) urlImages = urlImages + ", " + urlTwo;
                if (urlThree != null) urlImages = urlImages + ", " + urlThree;
                if (latitude != 0.0 && longitude != 0.0) {
                    mProprieteToUpdate.setLatitude(latitude);
                    mProprieteToUpdate.setLongitude(longitude);
                }
                new UpdateProprieteAsyncTask(mProprieteToUpdate).execute();

            } else {
//                new SaveProprieteAsyncTask(propriete).execute();
            }
        } else {
            Toast.makeText(mActivity, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.REQUEST_TAKE_PHOTO_PROPRIETE) {
            selectedPhotoRv.setVisibility(View.VISIBLE);
            mSelectedPhotoAdapter.addPhotoPath(mCurrentPhotoPath);
            mSelectedPhotoAdapter.notifyDataSetChanged();
        }

//        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
//            mPhotoPaths = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
//            Log.e(TAG, "onActivityResult: " + mPhotoPaths.size());
//
//            if(mPhotoPaths.size() > 0) {
//                selectedPhotoRv.setVisibility(View.VISIBLE);
//                mSelectedPhotoAdapter.addPhotoPaths(mPhotoPaths);
//                mSelectedPhotoAdapter.notifyDataSetChanged();
//            }
//        }
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

    private void dispatchTakePictureIntent(int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String imageFileName = null;

        if (requestCode == Constant.REQUEST_TAKE_PHOTO_PROPRIETE) {
            imageFileName =  "PROPRIETE_" + timeStamp;
        }

        photoFile = new File(FIleStorage.GetStorageDir(), imageFileName);
        mCurrentPhotoPath = photoFile.getAbsolutePath();

        if (photoFile != null) {
            Uri photoURI = Uri.fromFile(photoFile);
            takePictureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, requestCode);
        }
    }

    void initData(Propriete propriete) {
        mAdresseET.setText(propriete.getAdresse());
//        selectValue(mTypeProprieteSP, propriete.getType());
    }

    /**
     * Selectionne la Valeur passe au Spinner
     * @param spinner
     * @param value
     */
    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                Log.e(TAG, "selectValue: " + spinner.getItemAtPosition(i) );
                spinner.setSelection(i);
                break;
            }
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

    class UpdateProprieteAsyncTask extends AsyncTask<Void, Void, Integer> {
        Propriete propriete;

        public UpdateProprieteAsyncTask(Propriete propriete) {
            this.propriete = propriete;
        }

        @Override
        protected void onPostExecute(Integer rowsUpdated) {
            super.onPostExecute(rowsUpdated);

            if (rowsUpdated > 0) {
                Toast.makeText(mActivity, "Propriete mis a jour", Toast.LENGTH_SHORT).show();
                mActivity.finish();
            }
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return AcgtExpDatabase.getInstance().getIProprieteDao().update(propriete);
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

    class GetProprieteAsyncTask extends AsyncTask<Void, Void, Propriete> {
        long codePropriete;

        public GetProprieteAsyncTask(long codePropriete) {
            this.codePropriete = codePropriete;
        }

        @Override
        protected void onPostExecute(Propriete propriete) {
            super.onPostExecute(propriete);

            mProprieteToUpdate = propriete;
            initData(mProprieteToUpdate);
        }

        @Override
        protected Propriete doInBackground(Void... voids) {
            return AcgtExpDatabase.getInstance().getIProprieteDao().get(codePropriete);
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
