package spm.mymapdemo;

/**
 * Created by webwerks on 3/2/16.
 */

import com.google.android.gms.maps.*;
        import com.google.android.gms.maps.model.*;
        import android.app.Activity;
        import android.os.Bundle;

public class IndoreMapActivity extends Activity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changemaptype_layout);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Some buildings have indoor maps. Center the camera over
        // the building, and a floor picker will automatically appear.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-33.86997, 151.2089), 18));
    }
}