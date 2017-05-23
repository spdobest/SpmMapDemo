package spm.mymapdemo.retain_markers;

/**
 * Created by webwerks on 3/2/16.
 */

import android.app.Dialog;
        import android.os.Bundle;
        import android.support.v4.app.FragmentActivity;
        import android.view.Menu;
        import android.widget.Toast;

        import com.google.android.gms.common.ConnectionResult;
        import com.google.android.gms.common.GooglePlayServicesUtil;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import spm.mymapdemo.R;

public class RetainMarkerActivity extends FragmentActivity {

    GoogleMap mGoogleMap;

    ArrayList<LatLng> pointList = new ArrayList<LatLng>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_main);

        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }else { // Google Play Services are available

            // Getting reference to the SupportMapFragment of activity_main.xml
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            // Getting GoogleMap object from the fragment
            mGoogleMap = fm.getMap();

            // Enabling MyLocation Layer of Google Map
            mGoogleMap.setMyLocationEnabled(true);

            mGoogleMap.setOnMapClickListener(new OnMapClickListener(){

                @Override
                public void onMapClick(LatLng point) {

                    // Drawing the currently touched marker on the map
                    drawMarker(point);

                    // Adding the currently created marker position to the arraylist
                    pointList.add(point);

                    Toast.makeText(getBaseContext(), "Marker is added to the Map", Toast.LENGTH_SHORT).show();
                }
            });

            // Restoring the markers on configuration changes
            if(savedInstanceState!=null){
                if(savedInstanceState.containsKey("points")){
                    pointList = savedInstanceState.getParcelableArrayList("points");
                    if(pointList!=null){
                        for(int i=0;i<pointList.size();i++){
                            drawMarker(pointList.get(i));
                        }
                    }
                }
            }
        }
    }

    // Draw a marker at the "point"
    private void drawMarker(LatLng point){
        // Creating an instance of MarkerOptions
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting latitude and longitude for the marker
        markerOptions.position(point);

        // Setting a title for this marker
        markerOptions.title("Lat:"+point.latitude+","+"Lng:"+point.longitude);

        // Adding marker on the Google Map
        mGoogleMap.addMarker(markerOptions);
    }

    // A callback method, which is invoked on configuration is changed
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Adding the pointList arraylist to Bundle
        outState.putParcelableArrayList("points", pointList);

        // Saving the bundle
        super.onSaveInstanceState(outState);
    }
}