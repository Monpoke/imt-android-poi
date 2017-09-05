package monpoke.com.mypointofinterest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import monpoke.com.mypointofinterest.core.Codes;
import monpoke.com.mypointofinterest.core.DBPoi;
import monpoke.com.mypointofinterest.dto.PointOfInterest;

public class POIMainActivity extends AppCompatActivity {

    // the adapter
    private ArrayAdapter<PointOfInterest> adapterForPoi;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_item_create:
                createNewItem();
                break;
        }
        return true;
    }

    // On result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Toast.makeText(getApplicationContext(),"returned",Toast.LENGTH_SHORT).show();
        adapterForPoi.notifyDataSetChanged();


        if(requestCode==Codes.POI_MAIN){
            //Toast.makeText(getApplicationContext(),"hmmm",Toast.LENGTH_SHORT).show();
            if(resultCode==Codes.CANCELED){
                Toast.makeText(getApplicationContext(),"Canceled",Toast.LENGTH_SHORT).show();
            }
            else if(resultCode==Codes.OK){
                Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Open a new activity
     */
    private void createNewItem() {
        Toast.makeText(getApplicationContext(), "Create new item", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, Codes.POI_MAIN);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poimain);

        // List POI

        final ArrayList<PointOfInterest> listOFPOI = getPOI();

        // Get the POI View
        ListView listpoi = (ListView) findViewById(R.id.list_poi);
        adapterForPoi = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, listOFPOI);
        listpoi.setAdapter(adapterForPoi);

        // Setting a listener on click listener
        listpoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PointOfInterest selectedPOI = listOFPOI.get(position);
                Toast.makeText(getApplicationContext(), "Going to " + selectedPOI.getTitle(), Toast.LENGTH_LONG).show();
                openGoogleMaps(selectedPOI);
            }
        });


    }

    /**
     * List of POI
     * @return
     */
    private ArrayList<PointOfInterest> getPOI() {
       return DBPoi.list;
    }


    /**
     * Opening a map targeting a poi
     * @param pointOfInterest
     */
    private void openGoogleMaps(PointOfInterest pointOfInterest) {


        Uri gmmIntentUri = Uri.parse("geo:"+pointOfInterest.getGeo_lat()+","+pointOfInterest.getGeo_long());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            // MAps NoT INSTALLED
            Toast.makeText(getApplicationContext(), "Maps not installed", Toast.LENGTH_LONG).show();

        }

    }




}
