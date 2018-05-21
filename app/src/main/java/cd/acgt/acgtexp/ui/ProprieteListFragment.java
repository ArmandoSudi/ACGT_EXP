package cd.acgt.acgtexp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cd.acgt.acgtexp.Constant;
import cd.acgt.acgtexp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProprieteListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProprieteListFragment extends Fragment {

    private int mCodeProjet;


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
    public static ProprieteListFragment newInstance(int codeProject) {
        ProprieteListFragment fragment = new ProprieteListFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.KEY_CODE_PROJECT, codeProject);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCodeProjet = getArguments().getInt(Constant.KEY_CODE_PROJECT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_propriete_list, container, false);
        TextView testTV = view.findViewById(R.id.test);

        testTV.setText("code projet : " + mCodeProjet);

        return view;
    }

}
