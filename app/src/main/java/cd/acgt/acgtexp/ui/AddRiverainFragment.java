package cd.acgt.acgtexp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cd.acgt.acgtexp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRiverainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRiverainFragment extends Fragment {

    public AddRiverainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddRiverainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRiverainFragment newInstance() {
        AddRiverainFragment fragment = new AddRiverainFragment();
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
        return inflater.inflate(R.layout.fragment_add_proprietaire, container, false);
    }

}
