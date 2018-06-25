package cd.acgt.acgtexp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.entites.InfoWindowData;

/**
 * Created by Sugar on 6/19/2018
 */
public class CustomInfoWindowGoogleMapAdapter implements GoogleMap.InfoWindowAdapter {

    public Context mContext;

    public CustomInfoWindowGoogleMapAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)mContext).getLayoutInflater().inflate(R.layout.custom_info_window, null);

        ImageView mainImageIV = view.findViewById(R.id.main_image_iv);
        TextView typeProprieteTV = view.findViewById(R.id.type_propriete_tv);
        TextView adresseTV = view.findViewById(R.id.adresse_tv);

        InfoWindowData info = (InfoWindowData) marker.getTag();

        typeProprieteTV.setText(info.getType());
        adresseTV.setText(info.getAdresse());
        mainImageIV.setImageResource(R.mipmap.ic_launcher);

        return view;
    }
}
