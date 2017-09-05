package monpoke.com.mypointofinterest.core;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

import monpoke.com.mypointofinterest.dto.PointOfInterest;

/**
 * Created by A643012 on 05/09/2017.
 */

public class DBPoi {

    public final static ArrayList<PointOfInterest> list = new ArrayList<>();


    static {
        list.add(new PointOfInterest("Tour Eiffel", "BAD", 50.610724, 3.139041));
        list.add(new PointOfInterest("Chez Clem", "waouh", 43.939849, 2.438279));
        list.add(new PointOfInterest("Chez Alex", "waouh", 1.22222222, 1.222222));
        list.add(new PointOfInterest("Chez Ludo", "waouh", 39.409984, -5.304274));
        list.add(new PointOfInterest("Ma maison", "trop cool", 1.2224272, 1.2782));
    }

}
