package monpoke.com.mypointofinterest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import monpoke.com.mypointofinterest.core.DBPoi;
import monpoke.com.mypointofinterest.dto.PointOfInterest;

/**
 * Created by A643012 on 06/09/2017.
 */

public class InfoPopupFragment extends DialogFragment {

    public InfoPopupFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final PointOfInterest selected = (PointOfInterest) getArguments().getSerializable("POI");
        if(selected==null){
            return null;
        }

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(selected.getTitle() + "\n" + selected.getGeo_lat()+":"+selected.getGeo_long())

                .setPositiveButton(R.string.txtRemove, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int position) {

                        // remove and update
                        int index = DBPoi.list.indexOf(selected);
                        DBPoi.list.remove(index);

                        ((POIMainActivity)getActivity()).refreshData();
                    }
                })
                .setNegativeButton(R.string.txtCancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // just close, do nothing
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }





}
