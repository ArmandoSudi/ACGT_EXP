package cd.acgt.acgtexp.ui;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.TypePropriete;
import cd.acgt.acgtexp.utils.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTypeProprieteFragment extends Fragment {

    private static final String TAG = "AddTypeProprieteFragmen";

    private EditText mLongueurET, mLargeurET, mSurfaceeET, mVolumeET, mTypeMateriauET, mNombreET, mCoutUnitaireET, mMontantET, mDateSignatureProcoleET, mAutreInfoET, mObservationET, mDateAnnulationET, mMotifET;
    private Spinner mTypePropriete;
    Date mDateAnnulation, mDateEnregistrement, mDateSignatureProtocole;

    String mTypeMateriau , mAutreInfo, mObservation, mMotif, mType;
    int mNombre;
    long mCodePropriete;
    double mLongueur, mLargeur, mVolume, mCoutUnitaire, mMontant, mSurface;

    private Calendar mCalendar = Calendar.getInstance();
    Activity mActivity;

    boolean isBatisOrArbre = true;

    public AddTypeProprieteFragment() {
        // Required empty public constructor
    }

    public static AddTypeProprieteFragment newInstance(long codePropriete) {

        Bundle args = new Bundle();
        args.putLong(Constant.KEY_CODE_PROPRIETE, codePropriete);
        AddTypeProprieteFragment fragment = new AddTypeProprieteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = getActivity();

        if (getArguments() != null) {
            mCodePropriete = getArguments().getLong(Constant.KEY_CODE_PROPRIETE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_type_propriete, container, false);

        initView(view);

        final DatePickerDialog.OnDateSetListener dateAnnulationListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                mDateAnnulation = updateLabel(mDateAnnulationET);
            }
        };

        final DatePickerDialog.OnDateSetListener dateSignatureProtocoleListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                mDateSignatureProtocole = updateLabel(mDateSignatureProcoleET);
            }
        };

        mDateAnnulationET = view.findViewById(R.id.date_annulation_et);
        mDateAnnulationET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(mActivity, dateAnnulationListener, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mDateSignatureProcoleET = view.findViewById(R.id.date_signature_protocole_et);
        mDateSignatureProcoleET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(mActivity, dateSignatureProtocoleListener, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return view;
    }

    public Date updateLabel(EditText editText) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
        editText.setText(sdf.format(mCalendar.getTime()));
        return mCalendar.getTime();
    }

    public void initView(View view) {
        mLongueurET = view.findViewById(R.id.longueur_et);
        mLargeurET = view.findViewById(R.id.largeur_et);
        mSurfaceeET = view.findViewById(R.id.surface_et);
        mVolumeET = view.findViewById(R.id.volume_et);
        mTypeMateriauET = view.findViewById(R.id.type_materiau_et);
        mNombreET = view.findViewById(R.id.nombre_et);
        mCoutUnitaireET = view.findViewById(R.id.cout_unitaire_et);
        mMontantET = view.findViewById(R.id.montant_et);
        mAutreInfoET = view.findViewById(R.id.autre_information_et);
        mObservationET = view.findViewById(R.id.observation_et);
        mMotifET = view.findViewById(R.id.motif_et);

        //Default type of propriete selected is Batis
        toggleView("Batis");

        Spinner type = view.findViewById(R.id.type_propriete_sp);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mType = (String) adapterView.getItemAtPosition(i);
                toggleView(mType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button saveBT = view.findViewById(R.id.save_bt);
        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mActivity, "Enregistrement en cours...", Toast.LENGTH_SHORT).show();
                collectData();
            }
        });

        Button cancelBT = view.findViewById(R.id.cancel_bt);
        cancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.finish();
            }
        });
    }

    public void collectData() {
        mTypeMateriau = mTypeMateriauET.getText().toString();

        mLargeur = mLongueur = mSurface = mVolume = 0.0;

        if (!isBatisOrArbre) {
            mLongueur = Double.parseDouble(mLongueurET.getText().toString());
            mLargeur = Double.parseDouble(mLargeurET.getText().toString());
            mSurface = Double.parseDouble(mSurfaceeET.getText().toString());
            mVolume = Double.parseDouble(mVolumeET.getText().toString());
        }

        mTypeMateriau = mTypeMateriauET.getText().toString();
        mNombre = Integer.parseInt(mNombreET.getText().toString());
        mCoutUnitaire = Double.parseDouble(mCoutUnitaireET.getText().toString());
        mMontant = Double.parseDouble(mMontantET.getText().toString());
        mAutreInfo = mAutreInfoET.getText().toString();
        mObservation = mObservationET.getText().toString();
        mMotif = mMotifET.getText().toString();

        mDateEnregistrement = new Date(mCalendar.getTimeInMillis());

        TypePropriete typePropriete = new TypePropriete(mCodePropriete, mType, mLongueur, mLargeur, mVolume, mSurface, mTypeMateriau, mNombre, mMontant, mAutreInfo, mObservation, mMotif);
        if (mDateEnregistrement != null) typePropriete.dateEnregistrement = mDateEnregistrement;
        if (mDateSignatureProtocole != null) typePropriete.signatureProtocolAccord = mDateSignatureProtocole;
        if (mDateAnnulation != null) typePropriete.dateAnnulation = mDateAnnulation;

        new SaveTypeProprieteAsyncTask(typePropriete).execute();
    }

    //TODO PASS IN a string of the type
    public void toggleView(String type) {
        switch(type){
            case "Batis":
                isBatisOrArbre = true;
                break;
            case "Mur de cloture":
                isBatisOrArbre = false;
                break;
            case "Mur de soutenement":
                isBatisOrArbre = false;
                break;
            case "Parcelle vide":
                isBatisOrArbre = false;
                break;
            case "Arbre":
                isBatisOrArbre = true;
                break;
        }
        if (isBatisOrArbre) {
            mLongueurET.setVisibility(View.GONE);
            mLargeurET.setVisibility(View.GONE);
            mSurfaceeET.setVisibility(View.GONE);
            mVolumeET.setVisibility(View.GONE);
        } else {
            mLongueurET.setVisibility(View.VISIBLE);
            mLargeurET.setVisibility(View.VISIBLE);
            mSurfaceeET.setVisibility(View.VISIBLE);
            mVolumeET.setVisibility(View.VISIBLE);
        }


    }

    class SaveTypeProprieteAsyncTask extends AsyncTask<Void, Void, long[]>{
        TypePropriete typePropriete;

        public SaveTypeProprieteAsyncTask(TypePropriete typePropriete) {
            this.typePropriete = typePropriete;
        }

        @Override
        protected void onPostExecute(long[] rowId) {
            super.onPostExecute(rowId);

            if (rowId[0] > 0) {
                Toast.makeText(mActivity, "TypePropriete enregistre", Toast.LENGTH_SHORT).show();
                mActivity.finish();
            }
        }

        @Override
        protected long[] doInBackground(Void... voids) {
            long rowId[] = AcgtExpDatabase.getInstance().getITypeProprieteDao().insert(typePropriete);
            Log.e(TAG, "doInBackground: TypePropriete ID: " + rowId[0] );

            return rowId;
        }
    }
}
