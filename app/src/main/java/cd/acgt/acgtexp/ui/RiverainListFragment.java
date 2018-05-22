package cd.acgt.acgtexp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cd.acgt.acgtexp.Constant;
import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.adapters.RiverainAdapter;
import cd.acgt.acgtexp.entites.Riverain;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RiverainListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RiverainListFragment extends Fragment {

    private int mCodeProject;
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
    public static RiverainListFragment newInstance(int codeProjet) {
        RiverainListFragment fragment = new RiverainListFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.KEY_CODE_PROJECT, codeProjet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCodeProject = getArguments().getInt(Constant.KEY_CODE_PROJECT);
        }

        mRiverainAdapter = new RiverainAdapter(getActivity(), populateRiverain());
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

    List<Riverain> populateRiverain() {
        List<Riverain> riverains = new ArrayList<>();
        riverains.add(new Riverain("John Doe", "Av du fleuve numero 4", "0999999999", "Personne physique"));
        riverains.add(new Riverain("John Doe", "Av du fleuve numero 4", "0999999999", "Personne physique"));
        riverains.add(new Riverain("John Doe", "Av du fleuve numero 4", "0999999999", "Personne physique"));
        riverains.add(new Riverain("John Doe", "Av du fleuve numero 4", "0999999999", "Personne physique"));
        riverains.add(new Riverain("John Doe", "Av du fleuve numero 4", "0999999999", "Personne physique"));
        riverains.add(new Riverain("John Doe", "Av du fleuve numero 4", "0999999999", "Personne physique"));

        return riverains;
    }

}
