package com.scotts.cordova.beacon;

import android.app.Application;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

public class ScottsBeaconApplication extends Application implements BootstrapNotifier {

    public static final String TAG = ScottsBeaconApplication.class.getSimpleName();

    private RegionBootstrap mRegionBootstrap;
    private BeaconManager mBeaconManager;

    public void onCreate() {
        Log.d(TAG, "onCreate");

        mBeaconManager = BeaconManager.getInstanceForApplication(this);
        mBeaconManager.getBeaconParsers().add(
                new BeaconParser().setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24")
        );

        mRegionBootstrap = new RegionBootstrap(this,
                new Region("water_low",
                        Identifier.parse("58A78BF8-E280-48A4-8668-B8D8CF947CF8"),
                        Identifier.parse("1"),
                        Identifier.parse("64"))
        );
    }

    @Override
    public void didDetermineStateForRegion(int state, Region region) {
        Log.d(TAG, "didDetermineStateForRegion: " + state + ", " + (region == null ? "-" : region.toString()));
    }

    @Override
    public void didEnterRegion(Region region) {
        Log.d(TAG, "didEnterRegion: " + (region == null ? "-" : region.toString()));
    }

    @Override
    public void didExitRegion(Region region) {
        Log.d(TAG, "didExitRegion: " + (region == null ? "-" : region.toString()));
    }
}