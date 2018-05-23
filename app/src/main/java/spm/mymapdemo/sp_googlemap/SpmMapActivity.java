package spm.mymapdemo.sp_googlemap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.internal.zzf;

import spm.mymapdemo.R;

public class SpmMapActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

	private GoogleMap mMap;
	private ProgressBar mProgressBar;
	private CountDownTimer mCountDownTimer;
	private int count = 0;
	private LocationManager mLocationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spm_map);
		mProgressBar = findViewById(R.id.progressBar);
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);

		mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		try {
			if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				return;
			}
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000,
					500, this);
		}
		catch (NullPointerException e){
			e.printStackTrace();
		}
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;

		if(mLocationManager!=null){
		}

		// Add a marker in Mumbai, India, and move the camera.
		LatLng mumbai = new LatLng(19.07, 72.87);
		mMap.addMarker(new MarkerOptions().position(mumbai).title("Mumbai"));
		mMap.moveCamera(CameraUpdateFactory.newLatLng(mumbai));

		showProgress();
	}

	private void showProgress(){
		mProgressBar.setProgress(count);
		mCountDownTimer=new CountDownTimer(60000,2000) {

			@Override
			public void onTick(long millisUntilFinished) {
				Log.v("Log_tag", "Tick of Progress"+ count+ millisUntilFinished);
				count++;
				mProgressBar.setProgress((int)count*100/(60000/2000));

			}

			@Override
			public void onFinish() {
				//Do what you want
				count++;
				mProgressBar.setProgress(100);
			}
		};
		mCountDownTimer.start();
	}

	@Override
	public void onLocationChanged(Location location) {
		LatLng curentPosition = new LatLng(location.getLatitude(), location.getLongitude());

		MarkerOptions marker = new MarkerOptions()
				.position(new LatLng(-19.07, 72.87));

		mMap.addMarker(marker);


		if(location!=null) {
			marker.position(new LatLng(location.getLatitude(), location.getLongitude()));
			mMap.addMarker(marker);
		}
		// animateMarker(marker,curentPosition,true);
	}

	@Override
	public void onStatusChanged(String s, int i, Bundle bundle) {

	}

	@Override
	public void onProviderEnabled(String s) {

	}

	@Override
	public void onProviderDisabled(String s) {

	}
	public void animateMarker(final Marker marker, final LatLng toPosition,
							  final boolean hideMarker) {

		if(mMap!=null) {
			mMap.clear();

			final Handler handler = new Handler();
			final long start = SystemClock.uptimeMillis();
			Projection proj = mMap.getProjection();
			Point startPoint = proj.toScreenLocation(marker.getPosition());
			final LatLng startLatLng = proj.fromScreenLocation(startPoint);
			final long duration = 500;

			final Interpolator interpolator = new LinearInterpolator();



			handler.post(new Runnable() {
				@Override
				public void run() {
					long elapsed = SystemClock.uptimeMillis() - start;
					float t = interpolator.getInterpolation((float) elapsed
							/ duration);
					double lng = t * toPosition.longitude + (1 - t)
							* startLatLng.longitude;
					double lat = t * toPosition.latitude + (1 - t)
							* startLatLng.latitude;
					marker.setPosition(new LatLng(lat, lng));

					if (t < 1.0) {
						// Post again 16ms later.
						handler.postDelayed(this, 16);
					} else {
						if (hideMarker) {
							marker.setVisible(false);
						} else {
							marker.setVisible(true);
						}
					}
				}
			});
		}
	}
}
