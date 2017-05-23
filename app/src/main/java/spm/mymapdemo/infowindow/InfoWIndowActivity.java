package spm.mymapdemo.infowindow;

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
        import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
        import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;

import spm.mymapdemo.R;

public class InfoWIndowActivity extends FragmentActivity {

    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_main);
        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }else { // Google Play Services are available

            // Getting reference to the SupportMapFragment of activity_main.xml
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            // Getting GoogleMap object from the fragment
            googleMap = fm.getMap();

            // Enabling MyLocation Layer of Google Map
            googleMap.setMyLocationEnabled(true);

            googleMap.setOnMapClickListener(new OnMapClickListener() {

                @Override
                public void onMapClick(LatLng point) {
                    drawMarker(point);
                }
            });

            googleMap.setOnMarkerDragListener(new OnMarkerDragListener() {

                @Override
                public void onMarkerDragStart(Marker marker) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onMarkerDragEnd(Marker marker) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onMarkerDrag(Marker marker) {

                    // Getting the current position of the marker
                    LatLng pos = marker.getPosition();

                    // Updating the infowindow contents with the new marker coordinates
                    marker.setSnippet(pos.latitude + "," + pos.longitude);

                    // Updating the infowindow for the user
                    marker.showInfoWindow();

                }
            });
        }
    }

    private void drawMarker(LatLng point){
        // Creating an instance of MarkerOptions
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting latitude and longitude for the marker
        markerOptions.position(point);

        // Making this marker draggable
        markerOptions.draggable(true);

        // Title for this marker
        markerOptions.title("Marker Coordinates");

        // Coordinates for this marker as infowindow contents
        markerOptions.snippet(Double.toString(point.latitude)+","+Double.toString(point.longitude));

        // Adding marker on the Google Map
        googleMap.addMarker(markerOptions);
    }

}