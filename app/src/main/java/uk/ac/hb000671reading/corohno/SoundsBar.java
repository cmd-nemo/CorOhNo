package uk.ac.hb000671reading.corohno;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

//Sound class tha holds all the sfx data for the game
public class SoundsBar {
    //access modifiers to set variables
    private static SoundPool soundPool;
    private static int scorePointSound;
    private static int hitCoronaSound;
    private final int SOUND_POOL_MAX = 2;

    /**
     *
     * @param context
     */
    public SoundsBar(Context context) {
//sound class and locale varible for parsing volume
        soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC, 0);

        // Load sounds.
        scorePointSound = soundPool.load(context, R.raw.hit, 1); //sound for hiting obstacles
        hitCoronaSound = soundPool.load(context, R.raw.over, 1); //sound for when coliding with the corona
    }

    /**
     * plays the sound when user collides with useful obstacles.
     */
    public static void playScorePointSound () {
        soundPool.play(scorePointSound, 1.0f, 1.0f, 1, 0, 1.0f); //tuned at an optimized decibal rate
    }
    public static void playHitCoronaSound () {
        soundPool.play(hitCoronaSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}