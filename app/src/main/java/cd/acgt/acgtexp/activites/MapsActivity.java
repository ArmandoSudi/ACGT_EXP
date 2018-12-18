package cd.acgt.acgtexp.activites;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.adapters.CustomInfoWindowGoogleMapAdapter;
import cd.acgt.acgtexp.database.AcgtExpDatabase;
import cd.acgt.acgtexp.entites.InfoWindowData;
import cd.acgt.acgtexp.entites.Propriete;
import cd.acgt.acgtexp.ui.DetailsProprieteFragment;
import cd.acgt.acgtexp.utils.Constant;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private static final String TAG = "MapsActivity";

    private GoogleMap mMap;
    String mCodeProjet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        mCodeProjet = intent.getStringExtra(Constant.KEY_CODE_PROJECT);

        double lat = intent.getDoubleExtra(Constant.KEY_LATITUDE, 0.0);
        double lng = intent.getDoubleExtra(Constant.KEY_LONGITUDE, 0.0);

        Log.e(TAG, "onCreate: Latitude: " + lat + " longitude: " + lng );

        Toast.makeText(this, "code projet: " + mCodeProjet , Toast.LENGTH_SHORT).show();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);

        mMap.setInfoWindowAdapter(new CustomInfoWindowGoogleMapAdapter(this));
//        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(this, R.string.style_json);
//        boolean success = googleMap.setMapStyle(style);
//
//        if (!success) {
//            Log.e(TAG, "Style parsing failed.");
//        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng safricas = new LatLng(-4.3231408, 15.2782330);
        LatLng middle = new LatLng(-4.3237496, 15.2811704);
        LatLng isipa = new LatLng(-4.3262346, 15.2891322);

        // Get propriete and set their markers on the maps.
        getProprietes(mCodeProjet, googleMap);

//        Polyline polyline = googleMap.addPolyline(new PolylineOptions().
//            clickable(true).
//            add(safricas, middle, isipa));
//        mMap.addMarker(new MarkerOptions().position(safricas).title("Marker at Safricas"));
//        mMap.addMarker(new MarkerOptions().position(middle).title("Marker at Prince de liege"));
//        mMap.addMarker(new MarkerOptions().position(isipa).title("Marker at Isipa"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(safricas));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(safricas, 15));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked: " + marker.getTag().toString(),
                Toast.LENGTH_SHORT).show();
    }

    public void getProprietes(final String codeProjet, final GoogleMap googleMap) {
        (new AsyncTask<Void, Void, List<Propriete>>() {
            @Override
            protected void onPostExecute(List<Propriete> proprietes) {
                super.onPostExecute(proprietes);

                setMarkers(proprietes, googleMap);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-4.3237496, 15.2811704), 15));
            }

            @Override
            protected List<Propriete> doInBackground(Void... voids) {
                return AcgtExpDatabase.getInstance().getIProprieteDao().getProprieteByProjet(codeProjet);

            }
        }).execute();
    }

    public void setMarkers(List<Propriete> proprietes, GoogleMap googleMap) {

        if (proprietes != null && proprietes.size() > 0) {
            for (Propriete propriete : proprietes) {
                InfoWindowData info = new InfoWindowData();
                info.setAdresse(propriete.getAdresse());
                info.setType(propriete.getType());
                info.setImageUrl(propriete.getUrlPhoto1());
                googleMap.addMarker(new MarkerOptions().position(new LatLng(propriete.getLatitude(), propriete.getLongitude())).title(propriete.getType())).setTag(info);

                Polyline polyline = googleMap.addPolyline(new PolylineOptions().
                    clickable(true).
                    add(new LatLng(propriete.getLatitude(), propriete.getLongitude())));
            }
        }
    }


}
