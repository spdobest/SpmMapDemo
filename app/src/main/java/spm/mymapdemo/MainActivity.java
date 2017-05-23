package spm.mymapdemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import spm.mymapdemo.drawing_driving_route.DrawingRouteActivity;
import spm.mymapdemo.drivingmyloc_to_dest.DrivingFromSOurce2Dest;
import spm.mymapdemo.infowindow.InfoWIndowActivity;
import spm.mymapdemo.multiple_proximity_alert.MainProximityActivity;
import spm.mymapdemo.nearbyplaces.NearPlaceActivity;
import spm.mymapdemo.polyline.PolylineActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,LocationListener {

    // google map tracker
    // http://javapapers.com/android/android-location-tracker-with-google-maps/
    // http://www.codeproject.com/Questions/731090/How-to-mve-marker-like-car-goes-on-road-in-android
    // live updating google map
    // https://www.pubnub.com/blog/2015-05-13-displaying-android-location-live-updating-map-google-maps/
    // searching address in google map
    // http://wptrafficanalyzer.in/blog/android-geocoding-showing-user-input-location-on-google-map-android-api-v2/

    /// moving marker like UBER (FLAT MARKER)

    // https://developers.google.com/maps/documentation/android-api/

  //  https://developers.google.com/places/android-api/

    //  http://android-arsenal.com/tag/59

    // http://android-arsenal.com/details/1/2902


    MarkerOptions marker;

    Button btnAddarker;
    Button btnCuctomrker;
    Button btnAnimateCamera;
    Button btnZoom;
    Button btnMoveCam2Loc;

    Button btMapType;
    Button btnCompas;
    Button btnMyLoc;
    Button btnRotateMap;
    Button btnTrafick;

    Button btDrawCircle;
    Button btnDrawPolygon;
    Button btnDrawPolyLine;
    Button btnDrawLine;
    Button btnBuildingEnable;


    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;

    static final LatLng TutorialsPoint = new LatLng(21, 57);
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setupUI();

        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Marker TP = googleMap.addMarker(new MarkerOptions().
                    position(TutorialsPoint).title("TutorialsPoint"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
      //  locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this); //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER

       /* if (locationManager != null) {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.removeUpdates(MainActivity.this);
            }
        }
*/
    }

    private void setupUI() {
        btnAddarker = (Button) findViewById(R.id.btnAddarker);
        btnCuctomrker = (Button) findViewById(R.id.btnCuctomrker);
        btnAnimateCamera = (Button) findViewById(R.id.btnAnimateCamera);
        btnZoom = (Button) findViewById(R.id.btnZoom);
        btnMoveCam2Loc = (Button) findViewById(R.id.btnMoveCam2Loc);

        btMapType = (Button) findViewById(R.id.btMapType);
        btnCompas = (Button) findViewById(R.id.btnCompas);
        btnMyLoc = (Button) findViewById(R.id.btnMyLoc);
        btnRotateMap = (Button) findViewById(R.id.btnRotateMap);
        btnTrafick = (Button) findViewById(R.id.btnTrafick);

        btDrawCircle = (Button) findViewById(R.id.btDrawCircle);
        btnDrawPolygon = (Button) findViewById(R.id.btnDrawPolygon);
        btnDrawPolyLine = (Button) findViewById(R.id.btnDrawPolyLine);
        btnDrawLine = (Button) findViewById(R.id.btnDrawLine);
        btnBuildingEnable = (Button) findViewById(R.id.btnBuildingEnable);


        btMapType.setOnClickListener(this);
        btnCompas.setOnClickListener(this);
        btnMyLoc.setOnClickListener(this);
        btnRotateMap.setOnClickListener(this);
        btnTrafick.setOnClickListener(this);

        btDrawCircle.setOnClickListener(this);
        btnDrawPolygon.setOnClickListener(this);
        btnDrawPolyLine.setOnClickListener(this);
        btnDrawLine.setOnClickListener(this);
        btnBuildingEnable.setOnClickListener(this);

        btnAddarker.setOnClickListener(this);
        btnCuctomrker.setOnClickListener(this);
        btnAnimateCamera.setOnClickListener(this);
        btnZoom.setOnClickListener(this);
        btnMoveCam2Loc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddarker:
                // latitude and longitude
                double latitude = 1312312312312f;
                double longitude = 1312312312312f;

                // create marker
                marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps ");

                // adding marker
                googleMap.addMarker(marker);
                break;

            case R.id.btnCuctomrker:
                // ROSE color icon
//              marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                double latitude1 = 1312312312312f;
                double longitude1 = 1312312312312f;
                // GREEN color icon
//                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                // create marker
                MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude1, longitude1)).title("Hello Maps");

                // Changing marker icon
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.common_google_signin_btn_icon_dark));

                // adding marker
                googleMap.addMarker(marker);

                break;

            case R.id.btnAnimateCamera:

                break;

            case R.id.btnZoom:
                googleMap.setMyLocationEnabled(true); // false to disable
                break;

            case R.id.btnMoveCam2Loc:
                CameraPosition cameraPosition = new CameraPosition.Builder().target(
                        new LatLng(17.385044, 78.486671)).zoom(12).build();

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                break;

            /*second row*/
            case R.id.btMapType:
                showMapType();
                ;
                break;

            case R.id.btnCompas:
                googleMap.getUiSettings().setCompassEnabled(true);
                break;

            case R.id.btnMyLoc:
                googleMap.setMyLocationEnabled(true);

                break;

            case R.id.btnRotateMap:
                googleMap.getUiSettings().setRotateGesturesEnabled(true);

                break;

            case R.id.btnTrafick:
                googleMap.setTrafficEnabled(true);
                break;

            /*3rd ROW*/
            case R.id.btDrawCircle:
                googleMap.addCircle(new CircleOptions()
                        .center(new LatLng(googleMap.getMyLocation().getLatitude(), googleMap.getMyLocation().getLongitude()))
                        .radius(100)
                        .strokeColor(Color.RED)
                        .fillColor(Color.BLUE));
                break;

            case R.id.btnDrawPolygon:

                break;

            case R.id.btnDrawPolyLine:
                //specify latitude and longitude of both source and destination
                Polyline line = googleMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(19.0222, 72.8666), new LatLng(19.0180, 72.8448))
                        .width(5)
                        .color(Color.RED));
                break;

            case R.id.btnDrawLine:

                break;

            case R.id.btnBuildingEnable:
                Intent intent = new Intent(MainActivity.this,PolylineActivity.class);
                startActivity(intent);
                break;
        }
    }

    // show map type list dialog
    public void showMapType() {
        List<String> mAnimals = new ArrayList<String>();
        mAnimals.add("Normal");
        mAnimals.add("Hybrid");
        mAnimals.add("Satelite");
        mAnimals.add("Teroin");
        mAnimals.add("NONE");
        //Create sequence of items
        final CharSequence[] Animals = mAnimals.toArray(new String[mAnimals.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Animals");
        dialogBuilder.setItems(Animals, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                googleMap.clear();
                switch (item) {

                    case 0:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                    case 1:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                    case 2:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;
                    case 3:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        break;
                    case 4:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                        break;
                }
            }
        });
        //Create alert dialog object via builder
        AlertDialog alertDialogObject = dialogBuilder.create();
        //Show the dialog
        alertDialogObject.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        googleMap.animateCamera(cameraUpdate);
//        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
    public void drawPath(String  result) {

        try {
            //Tranform the string into a json object
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);
            Polyline line = googleMap.addPolyline(new PolylineOptions()
                            .addAll(list)
                            .width(12)
                            .color(Color.parseColor("#05b1fb"))//Google maps blue color
                            .geodesic(true)
            );

          /* for(int z = 0; z<list.size()-1;z++){
                LatLng src= list.get(z);
                LatLng dest= list.get(z+1);
                Polyline line1 = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(src.latitude, src.longitude), new LatLng(dest.latitude,   dest.longitude))
                .width(2)
                .color(Color.BLUE).geodesic(true));
            }*/

        }
        catch (JSONException e) {

        }
    }
    public String makeURL (double sourcelat, double sourcelog, double destlat, double destlog ){
        StringBuilder urlString = new StringBuilder();
        urlString.append("http://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(Double.toString(sourcelat));
        urlString.append(",");
        urlString
                .append(Double.toString( sourcelog));
        urlString.append("&destination=");// to
        urlString
                .append(Double.toString( destlat));
        urlString.append(",");
        urlString.append(Double.toString( destlog));
        urlString.append("&sensor=false&mode=driving&alternatives=true");
        urlString.append("&key=YOUR_API_KEY");
        return urlString.toString();
    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng( (((double) lat / 1E5)),
                    (((double) lng / 1E5) ));
            poly.add(p);
        }

        return poly;
    }
    private class connectAsyncTask extends AsyncTask<Void, Void, String> {
        private ProgressDialog progressDialog;
        String url;
        connectAsyncTask(String urlPass){
            url = urlPass;
        }
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Fetching route, Please wait...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(Void... params) {
         //   http://stackoverflow.com/questions/14702621/answer-draw-path-between-two-points-using-google-maps-android-api-v2
           /* JSONParser jParser = new JSONParser();
            String json = jParser.getJSONFromUrl(url);*/
            String json = "";
            return json;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.hide();
            if(result!=null){
                drawPath(result);
            }
        }
    }
}
