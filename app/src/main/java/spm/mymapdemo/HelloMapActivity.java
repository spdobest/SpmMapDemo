package spm.mymapdemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by webwerks on 3/2/16.
 */
public class HelloMapActivity extends AppCompatActivity implements OnMapReadyCallback,View.OnClickListener {

    Button btnChangemap;
    Button btnIndoreMap;
    Button btnCustomMarker;
    Button btnFlatMarker;
    Button btnPolyLine;

    Button btMapType;
    Button btnCompas;
    Button btnMyLoc;
    Button btnRotateMap;
    Button btnTrafick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hellomap_activity);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setupUI();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng sydney = new LatLng(-33.867, 151.206);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
    }

    private void setupUI() {
        btnChangemap = (Button) findViewById(R.id.btnChangeMap);
        btnIndoreMap = (Button) findViewById(R.id.btnIndoreMap);
        btnCustomMarker = (Button) findViewById(R.id.btnCustomMarker);
        btnFlatMarker = (Button) findViewById(R.id.btnFlatMarker);
        btnPolyLine = (Button) findViewById(R.id.btnPolyLine);

        btMapType = (Button) findViewById(R.id.btMapType);
        btnCompas = (Button) findViewById(R.id.btnCompas);
        btnMyLoc = (Button) findViewById(R.id.btnMyLoc);
        btnRotateMap = (Button) findViewById(R.id.btnRotateMap);
        btnTrafick = (Button) findViewById(R.id.btnTrafick);


        btMapType.setOnClickListener(this);
        btnCompas.setOnClickListener(this);
        btnMyLoc.setOnClickListener(this);
        btnRotateMap.setOnClickListener(this);
        btnTrafick.setOnClickListener(this);



        btnChangemap.setOnClickListener(this);
        btnIndoreMap.setOnClickListener(this);
        btnCustomMarker.setOnClickListener(this);
        btnFlatMarker.setOnClickListener(this);
        btnPolyLine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnChangeMap:
                    Intent intentChangeMap = new Intent(HelloMapActivity.this,ChangeMapTypeActivity.class);
                startActivity(intentChangeMap);

                break;

            case R.id.btnIndoreMap:
                Intent intentIndoreMap = new Intent(HelloMapActivity.this,IndoreMapActivity.class);
                startActivity(intentIndoreMap);
                break;

            case R.id.btnCustomMarker:
                Intent intentCustomMarker = new Intent(HelloMapActivity.this,CustomMarketAndInfoWindow.class);
                startActivity(intentCustomMarker);
                break;

            case R.id.btnFlatMarker:
                Intent intentFlatMarker = new Intent(HelloMapActivity.this,FlatMarker.class);
                startActivity(intentFlatMarker);
                break;

            case R.id.btnPolyLine:
                Intent intentPolyLine= new Intent(HelloMapActivity.this,PolyLine.class);
                startActivity(intentPolyLine);
                break;

            /*second row*/
            case R.id.btMapType:

                break;

            case R.id.btnCompas:

                break;

            case R.id.btnMyLoc:

                break;

            case R.id.btnRotateMap:

                break;

            case R.id.btnTrafick:

                break;


        }
    }
}