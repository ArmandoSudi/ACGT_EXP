package cd.acgt.acgtexp.ui;


import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import cd.acgt.acgtexp.dao.IProprieteItemDao;
import cd.acgt.acgtexp.entites.LotExpropriation;
import cd.acgt.acgtexp.utils.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.adapters.ProprieteAdapter;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Propriete;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProprieteListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProprieteListFragment extends Fragment {

    private String mCodeProjet;
    private ProprieteAdapter mProprieteAdapter;
    Spinner mLotSpinner;
    Activity mActivity;

    public ProprieteListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param codeProject the ID of the project for which we'll be listing.
     * @return A new instance of fragment ProprieteListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProprieteListFragment newInstance(String codeProject) {
        ProprieteListFragment fragment = new ProprieteListFragment();
        Bundle args = new Bundle();
        args.putString(Constant.KEY_CODE_PROJECT, codeProject);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCodeProjet = getArguments().getString(Constant.KEY_CODE_PROJECT);
        }
        mProprieteAdapter = new ProprieteAdapter(getActivity(), mCodeProjet);
        mActivity = getActivity();
        mLotSpinner = mActivity.findViewById(R.id.spinner_nav);

        new LoadLotAsyncTask(mCodeProjet, mLotSpinner, mActivity, mProprieteAdapter).execute();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mProprieteAdapter !=  null) {
            mProprieteAdapter.clear();
        }
        new LoadProprieteAsyncTask(mProprieteAdapter, mCodeProjet).execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_propriete_list, container, false);

        RecyclerView proprieteRV = view.findViewById(R.id.propiete_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        proprieteRV.setLayoutManager(linearLayoutManager);
        proprieteRV.setHasFixedSize(true);
        proprieteRV.addItemDecoration(new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation()));
        proprieteRV.setAdapter(mProprieteAdapter);

        return view;
    }

    static class LoadProprieteAsyncTask extends AsyncTask<Void, Void, List<Propriete>> {

        private static final String TAG = "LoadProprieteAsyncTask";
        ProprieteAdapter proprieteAdapter;
        String codeLot;

        public LoadProprieteAsyncTask(ProprieteAdapter proprieteAdapter, String codeLot) {
            this.proprieteAdapter = proprieteAdapter;
            this.codeLot = codeLot;
        }

        @Override
        protected void onPostExecute(List<Propriete> proprietes) {
            super.onPostExecute(proprietes);
            Log.e(TAG, "onPostExecute: " + proprietes.size() );
            proprieteAdapter.add(proprietes);
            proprieteAdapter.notifyDataSetChanged();
        }

        @Override
        protected List<Propriete> doInBackground(Void... voids) {
            return AcgtExpDatabase.getInstance().getIProprieteDao().getProprieteByLot(codeLot);
        }
    }

    static class LoadLotAsyncTask extends AsyncTask<Void, Void, List<LotExpropriation>>{

        private static final String TAG = "LoadLotAsyncTask";
        String mCodeProjet;
        Spinner mLotSpinner;
        Activity mActivity;
        ProprieteAdapter proprieteAdapter;

        public LoadLotAsyncTask(String codeProjet, Spinner lotSpinner, Activity activity, ProprieteAdapter proprieteAdapter){
            this.mCodeProjet = codeProjet;
            this.mLotSpinner = lotSpinner;
            this.mActivity = activity;
            this.proprieteAdapter = proprieteAdapter;
        }

        @Override
        protected void onPostExecute(List<LotExpropriation> lotExpropriations) {
            super.onPostExecute(lotExpropriations);

            if (lotExpropriations != null && lotExpropriations.size()>0){
                mLotSpinner.setAdapter(new ArrayAdapter<LotExpropriation>(mActivity, R.layout.spinner_item_white, lotExpropriations));
                mLotSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        LotExpropriation lot = (LotExpropriation) parent.getItemAtPosition(position);
                        Log.e(TAG, "onItemSelected: " + lot.codeLotExpropriation );
                        new LoadProprieteAsyncTask(proprieteAdapter, lot.codeLotExpropriation).execute();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }


        }

        @Override
        protected List<LotExpropriation> doInBackground(Void... voids) {
            return AcgtExpDatabase.getInstance().getILotExpropriationDao().get(mCodeProjet);
        }
    }

}
