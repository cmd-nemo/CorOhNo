package uk.ac.hb000671reading.corohno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
/*
import com.google.android.gms.ads.AdListener;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
*/


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private GameCanvas gameCanvas; //sets the game canvas scene
    private Handler handler = new Handler();
    private final static long TIMER_INTERVAL = 30; //game timer

    //private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_main);

        gameCanvas = new GameCanvas(this); //parses the game canvas R.layout.activity_main
        setContentView(gameCanvas);
        //setContentView(R.layout.activity_main);
        /*
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        //MobileAds.initialize(this, "ca-app-pub-1045737432982924~4256501403");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //mInterstitialAd.show();

        if (mInterstitialAd.isLoaded())
        {
            Log.i("Hello", "World");
            mInterstitialAd.show();
            Log.i("Hello", "World2");
        }

         */
/**
 * Timer class
 */

        Timer timer = new Timer(); //timer instantiateion
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    /**
                     * creates a runnable jar.
                     */
                    public void run() {
                        gameCanvas.invalidate();
                    }
                }); //handles invalid time
            }
        }, 0, TIMER_INTERVAL);

        //shows the timer intervals

        /*
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-1045737432982924/4894741752");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded(){
                interstitialAd.show();
            }
        });
         */

        /*
        MobileAds.initialize(this, "ca-app-pub-1045737432982924~4256501403");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1045737432982924/4894741752");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.show();

         */





    }
}