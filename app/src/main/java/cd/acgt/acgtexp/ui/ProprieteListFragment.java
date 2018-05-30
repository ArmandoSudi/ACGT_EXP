package cd.acgt.acgtexp.ui;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

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
        mProprieteAdapter = new ProprieteAdapter(getActivity());
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
        ProprieteAdapter proprieteAdapter;
        String codeProjet;

        public LoadProprieteAsyncTask(ProprieteAdapter proprieteAdapter, String codeProjet) {
            this.proprieteAdapter = proprieteAdapter;
            this.codeProjet = codeProjet;
        }

        @Override
        protected void onPostExecute(List<Propriete> proprietes) {
            super.onPostExecute(proprietes);
            proprieteAdapter.addAll(proprietes);
            proprieteAdapter.notifyDataSetChanged();
        }

        @Override
        protected List<Propriete> doInBackground(Void... voids) {
            return AcgtExpDatabase.getInstance().getIProprieteDao().getProprieteByProjet(codeProjet);
        }
    }

}
