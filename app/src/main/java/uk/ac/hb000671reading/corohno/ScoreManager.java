package uk.ac.hb000671reading.corohno;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * class to parse the score details through save within the system internal memory.
 */
public class ScoreManager {

    private static Context context;

    /**
     *
     * @param context
     */
    public ScoreManager(Context context){
        ScoreManager.context = context; //creates a context to score manager
    }

    /**
     * //defines the parameter Score
     * @param score
     */
    public void saveScore(int score){
        //memory saver of the scores internally
        SharedPreferences settings = context.getSharedPreferences("GAME_DATA",Context.MODE_PRIVATE); //shares the highscore
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("HIGH_SCORE", score);
        editor.commit();
    }

    /**
     * function when loading the highscore, to share the gameData
     * @return the highscore
     */
    public int loadHighScore(){
        SharedPreferences settings = context.getSharedPreferences("GAME_DATA",Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE",0);
        return highScore;
    }
}
