package cd.acgt.acgtexp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cd.acgt.acgtexp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsProprieteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsProprieteFragment extends Fragment {


    public DetailsProprieteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DetailsProprieteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsProprieteFragment newInstance() {
        DetailsProprieteFragment fragment = new DetailsProprieteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_propriete, container, false);
    }

}
