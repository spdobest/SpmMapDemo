package spm.mymapdemo.polyline;

/**
 * Created by webwerks on 3/2/16.
 */

import java.util.ArrayList;

        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.v4.app.FragmentActivity;
        import android.view.Menu;

        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
        import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.maps.model.PolylineOptions;

import spm.mymapdemo.R;

public class PolylineActivity extends FragmentActivity {

    GoogleMap googleMap;
    ArrayList<LatLng> points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polyline_activity);

        points = new ArrayList<LatLng>();

        // Getting reference to the SupportMapFragment of activity_main.xml
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting GoogleMap object from the fragment
        googleMap = fm.getMap();

        // Enabling MyLocation Layer of Google Map
        googleMap.setMyLocationEnabled(true);

        // Setting OnClick event listener for the Google Map
        googleMap.setOnMapClickListener(new OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                // Instantiating the class MarkerOptions to plot marker on the map
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting latitude and longitude of the marker position
                markerOptions.position(point);

                // Setting titile of the infowindow of the marker
                markerOptions.title("Position");

                // Setting the content of the infowindow of the marker
                markerOptions.snippet("Latitude:"+point.latitude+","+"Longitude:"+point.longitude);

                // Instantiating the class PolylineOptions to plot polyline in the map
                PolylineOptions polylineOptions = new PolylineOptions();

                // Setting the color of the polyline
                polylineOptions.color(Color.RED);

                // Setting the width of the polyline
                polylineOptions.width(3);

                // Adding the taped point to the ArrayList
                points.add(point);

                // Setting points of polyline
                polylineOptions.addAll(points);

                // Adding the polyline to the map
                googleMap.addPolyline(polylineOptions);

                // Adding the marker to the map
                googleMap.addMarker(markerOptions);

            }
        });

        googleMap.setOnMapLongClickListener(new OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point) {
                // Clearing the markers and polylines in the google map
                googleMap.clear();

                // Empty the array list
                points.clear();
            }
        });
    }


}


