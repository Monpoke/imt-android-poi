package monpoke.com.mypointofinterest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import monpoke.com.mypointofinterest.core.Codes;
import monpoke.com.mypointofinterest.core.DBPoi;
import monpoke.com.mypointofinterest.dto.PointOfInterest;

public class AddActivity extends AppCompatActivity implements LocationListener {

    private TextView lblPosition;
    private LocationManager locationManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        lblPosition = (TextView) findViewById(R.id.lblPosition);


        // location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    Codes.PERMISSION_GPS);


        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


        Button btnSubmit = (Button) findViewById(R.id.btnsubmit);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
                finish();

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txttitle = (TextView) findViewById(R.id.inputTitle);
                TextView txtDescription = (TextView) findViewById(R.id.inputDescription);
                RatingBar rating = (RatingBar) findViewById(R.id.rating);

                // block if no data
                if (txttitle.length() == 0 || txtDescription.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Empty inputs", Toast.LENGTH_LONG).show();
                    return;
                }

                double geo_lat = 0, geo_long = 0;

                PointOfInterest poi = new PointOfInterest(txttitle.getText().toString(), txtDescription.getText().toString(), geo_lat, geo_long);
                poi.setRating(rating.getRating());

                DBPoi.list.add(poi);
                finish();
            }
        });


    }

    /**
     * After the authorization
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Codes.PERMISSION_GPS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // have to update the GPS
                    updatePosition();

                } else {
                    Toast.makeText(getApplicationContext(), "Permission not allowed", Toast.LENGTH_LONG).show();
                    finish();
                }
                return;
            }

        }
    }

    /**
     * Update the position on the GUI
     */
    private void updatePosition() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            updatePosition(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
            return;
        }

    }
    private void updatePosition(Location location) {
        lblPosition.setText("Lat: "+ location.getLatitude()+"; Long: "+ location.getLongitude());
    }

    @Override
    public void onLocationChanged(Location location) {
        updatePosition(location);
    }

    // DON'T USE FOLLOWING METHODS
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}
