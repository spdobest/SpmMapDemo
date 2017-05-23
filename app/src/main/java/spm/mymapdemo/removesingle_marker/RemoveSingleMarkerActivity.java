package spm.mymapdemo.removesingle_marker;

/**
 * Created by webwerks on 3/2/16.
 */

import android.app.Dialog;
        import android.os.Bundle;
        import android.support.v4.app.FragmentActivity;
        import android.view.Menu;

        import com.google.android.gms.common.ConnectionResult;
        import com.google.android.gms.common.GooglePlayServicesUtil;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
        import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;

import spm.mymapdemo.R;

public class RemoveSingleMarkerActivity extends FragmentActivity {

    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_main);

        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if (status != ConnectionResult.SUCCESS) { // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        } else { // Google Play Services are available

            // Getting reference to the SupportMapFragment of activity_main.xml
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            // Getting GoogleMap object from the fragment
            googleMap = fm.getMap();

            // Enabling MyLocation Layer of Google Map
            googleMap.setMyLocationEnabled(true);

            // Setting click event handler for map
            googleMap.setOnMapClickListener(new OnMapClickListener() {

                @Override
                public void onMapClick(LatLng point) {
                    // Draw the marker at the taped position
                    drawMarker(point);
                }
            });

            // Setting click event handler for InfoWIndow
            googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

                @Override
                public void onInfoWindowClick(Marker marker) {
                    // Remove the marker
                    marker.remove();
                }
            });
        }
    }

    private void drawMarker(LatLng point) {
        // Creating an instance of MarkerOptions
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting latitude and longitude for the marker
        markerOptions.position(point);

        // Setting snippet for the InfoWindow
        markerOptions.snippet("Tap here to remove this marker");

        // Setting title for the InfoWindow
        markerOptions.title("Marker Demo");

        // Adding marker on the Google Map
        googleMap.addMarker(markerOptions);
    }
}