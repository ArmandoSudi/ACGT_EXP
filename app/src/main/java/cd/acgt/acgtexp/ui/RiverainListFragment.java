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
import cd.acgt.acgtexp.adapters.RiverainAdapter;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.Riverain;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RiverainListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RiverainListFragment extends Fragment {

    private String mCodeProject;
    RiverainAdapter mRiverainAdapter;


    public RiverainListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param codeProjet the ID of the project for which we'll be listing.
     * @return A new instance of fragment RiverainListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RiverainListFragment newInstance(String codeProjet) {
        RiverainListFragment fragment = new RiverainListFragment();
        Bundle args = new Bundle();
        args.putString(Constant.KEY_CODE_PROJECT, codeProjet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCodeProject = getArguments().getString(Constant.KEY_CODE_PROJECT);
        }

        mRiverainAdapter = new RiverainAdapter(getActivity(), mCodeProject);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mRiverainAdapter != null) {
            mRiverainAdapter.clear();
        }
        new LoadRiverrainAsyncTask(mRiverainAdapter, mCodeProject).execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riverain_list, container, false);

        RecyclerView riverainRV = view.findViewById(R.id.riverain_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        riverainRV.setLayoutManager(linearLayoutManager);
        riverainRV.setHasFixedSize(true);
        riverainRV.addItemDecoration(new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation()));
        riverainRV.setAdapter(mRiverainAdapter);

        return view;
    }

    static class LoadRiverrainAsyncTask extends AsyncTask<Void, Void, List<Riverain>> {
        RiverainAdapter riverainAdapter;
        String codeProjet;

        public LoadRiverrainAsyncTask(RiverainAdapter riverainAdapter, String codeProjet) {
            this.riverainAdapter = riverainAdapter;
            this.codeProjet = codeProjet;
        }

        @Override
        protected void onPostExecute(List<Riverain> riverains) {
            super.onPostExecute(riverains);
            riverainAdapter.addAll(riverains);
            riverainAdapter.notifyDataSetChanged();
        }

        @Override
        protected List<Riverain> doInBackground(Void... voids) {
            return AcgtExpDatabase.getInstance().getIRiverainDao().getRiverainByProjet(codeProjet);
        }
    }

}
