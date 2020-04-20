////////////////////////////************COR OH NO!****************////////////////////
package uk.ac.hb000671reading.corohno;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neyma Siddiqui 25/03/20
 */
public class GameCanvas extends View {

    //Canvas
    private int canvasWidth; // set canvas width
    private int canvasHeight; // set canvas height

    //Player
    //private Bitmap Player player;
    private Bitmap player[] = new Bitmap[2]; //parses bitmap to player to output overlay image
    private int playerX = 10; // player x position
    private int playerY;  // player y positon
    private int playerSpeed; //player speed

    //Obstacle
    private int sanitizerX = 10;  //sanitizer obstacle x position
    private int sanitizerY;  // sanitizer obstacle y position
    private int sanitizerSpeed = 12; // speed of the sanitizer object

    //obstacle 2
    private int glovesX = 5; //gloves obstacle x pos
    private int glovesY; // gloves obstacle y pos
    private int glovesSpeed = 8; // gloves obstacle speed

    //obstacle 3
    private int maskX = 2; //mask obstacle x pos
    private int maskY; //mask obstacle y pos
    private int maskSpeed = 2; //mask speed

    //Corona
    private List<CoronaObject> coronaObjectList = new ArrayList<>(); //arraylist of objects of CoronaVirus
    /**
     * The array list is a list of all the corona objects, these are held in the CoronaObject class.
     */

    //Background
    private Bitmap backgroundImage; //sets the variable to background image
    private Bitmap helpPage; //help page
    //Scorer
    private Paint scorerPaint = new Paint(); //score text variable parsed through system variable paint
    private int scorer; //variable for the scores

    private ScoreManager scoreManager; //calculates the highscore
    private int highScore; //sets a variable to the highscore

    //Levels
    private Paint levelsPaint = new Paint(); //text variable for levels

    //Lives
    private Bitmap[] lives = new Bitmap[2]; //image for hearts to show lives

    private int life_count; //live count variable to show total number of lives
    /////////////////////////////////////STATA CHECK\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    //Check status
    private boolean touch_flg = false;
    private int gameScene; //sets the game scene
    private static final int START_GAME = 0; //game order
    private static final int PLAY_GAME = 1; //play
    private static final int GAME_OVER = 2; //over

    /////////////////////////BUTTONS FOR THE START AND GAME OVER/////////////////////////////////////////
    //START/GAME OVER
    private Bitmap imageStart;
    private Bitmap imageGameOver;
    private Bitmap buttonStart;
    private Bitmap buttonReturn;
    private int imageButtonX;
    private int imageButtonY;
    private int imageHelp;
    private Paint highScoreTitlePaint = new Paint();

    //////////////////OBSTACLES//////////////////////////////////////////////////////////////////////////
    private final Bitmap sanitizer;
    private final Bitmap gloves;
    private final Bitmap mask;

    //Sound
    SoundsBar soundsBar; // sound variable through sound bar class

    /**
     *
     * @param context
     */
    public GameCanvas(Context context) {

        super(context); //parent class of context, defined in subclass

        player[0] = BitmapFactory.decodeResource(getResources(),R.drawable.doct1); //draws the player sprite image as standing
        player[1] = BitmapFactory.decodeResource(getResources(),R.drawable.doct2); //draws the player spite image as jumping

        imageStart = BitmapFactory.decodeResource(getResources(),R.drawable.start); //draws the start page image
        backgroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.hosppp); //draws the background image
        imageGameOver = BitmapFactory.decodeResource(getResources(),R.drawable.gameover); //draws the game over page image

        //Sanitizer
        sanitizer = BitmapFactory.decodeResource(getResources(), R.drawable.san1);  //draws the hand sanitizer object
        //gloves
        gloves = BitmapFactory.decodeResource(getResources(), R.drawable.gloves1); //draws the glove object
        //mask
        mask = BitmapFactory.decodeResource(getResources(), R.drawable.mask); //draws the mask object

/**
 *Below are all variables and modifiers set for the objects
 * @Param scorerPaint
 * sets the text layout for scores, levels, lives
 */

        scorerPaint.setColor(Color.BLUE);
        scorerPaint.setTextSize(35);
        scorerPaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorerPaint.setAntiAlias(true);

        levelsPaint.setColor((Color.BLACK));
        levelsPaint.setTextSize(34);
        levelsPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelsPaint.setTextAlign(Paint.Align.CENTER);
        levelsPaint.setAntiAlias(true);

        lives[0] = BitmapFactory.decodeResource(getResources(),R.drawable.heart);
        lives[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_g);
// Buttons set for the start and return
        buttonStart = BitmapFactory.decodeResource(getResources(),R.drawable.start_btn);
        buttonReturn = BitmapFactory.decodeResource(getResources(),R.drawable.return_btn);
//highscore title text
        highScoreTitlePaint.setTextSize(36);
        highScoreTitlePaint.setTypeface(Typeface.DEFAULT_BOLD);
        highScoreTitlePaint.setTextAlign(Paint.Align.CENTER);

        //Original Position

        gameScene = START_GAME; //starts the game
        //sound

        soundsBar = new SoundsBar(context); //gets the sound from the sound bar class

        //Score
        scoreManager = new ScoreManager(context); //manages the score from the score manager class
        highScore = scoreManager.loadHighScore(); //parses the highscore from the score manager based on game play

    }

    /**
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        canvasWidth = getWidth(); //get canvas width
        canvasHeight = getHeight(); //get canvas height

        imageButtonX = canvasWidth / 2 - buttonStart.getWidth()/2; //draws the image button on the background
        imageButtonY = canvasHeight/2 + (int)(buttonStart.getHeight()*1.5); //parses the pixel coords

        switch(gameScene){
            case START_GAME:
                Rect dest1 = new Rect(0, 0, getWidth(), getHeight());
                Paint paint1 = new Paint();
                paint1.setFilterBitmap(true);
                canvas.drawBitmap(imageStart,null,dest1,paint1);
                drawStartPage(canvas);
                break;
            case PLAY_GAME:
                Rect dest = new Rect(0, 0, getWidth(), getHeight());
                Paint paint = new Paint();
                paint.setFilterBitmap(true);
                canvas.drawBitmap(backgroundImage,null,dest,paint);
                drawPlayPage(canvas);
                break;
            case GAME_OVER:
                Rect dest2 = new Rect(0, 0, getWidth(), getHeight());
                Paint paint2 = new Paint();
                paint2.setFilterBitmap(true);
                canvas.drawBitmap(imageGameOver,null,dest2,paint2);
                drawGameOverPage(canvas);
                break;


        }

    }

    /**
     *
     * @param canvas
     */
    public void drawPlayPage(Canvas canvas){

        //Player
        int minPlayerY = player[0].getHeight(); //sets position of the player
        int maxPlayerY = canvasHeight - player[0].getHeight()*3;

        playerY += playerSpeed;
        if(playerY < minPlayerY) playerY = minPlayerY;
        if(playerY > maxPlayerY) playerY = maxPlayerY;
        playerSpeed +=2;

        if(touch_flg) {
            //Walk
            canvas.drawBitmap(player[1], playerX, playerY, null); //movement flag for player
            touch_flg = false;
        }
        else{
            canvas.drawBitmap(player[0],playerX, playerY, null);
        }
        //Sanitizer obstacle
        sanitizerX -= sanitizerSpeed;  //sanitizer obstacle movement
        if(collisionCheck(sanitizerX, sanitizerY)){
            scorer +=10;
            sanitizerX =- 100;
            soundsBar.playScorePointSound();
        }
        if(sanitizerX<0){
            sanitizerX = canvasWidth + 20;
            sanitizerY = (int)Math.floor(Math.random()*(maxPlayerY-minPlayerY))+minPlayerY;
        }
        canvas.drawBitmap(sanitizer, sanitizerX,sanitizerY,null);

        //gloves obstacle movement
        glovesX -= glovesSpeed;
        if(collisionCheck(glovesX, glovesY)){
            scorer +=20;
            glovesX =- 100;
            soundsBar.playScorePointSound();
        }
        if(glovesX<0){
            glovesX = canvasWidth + 20;
            glovesY = (int)Math.floor(Math.random()*(maxPlayerY-minPlayerY))+minPlayerY;
        }
        canvas.drawBitmap(gloves, glovesX,glovesY,null);

        //masks obstacle
        maskX -= maskSpeed; //mask obstacle movement
        if(collisionCheck(maskX, maskY)){
            scorer +=15;
            maskX =- 100;
            soundsBar.playScorePointSound();
        }
        if(maskX<0){
            maskX = canvasWidth + 20;
            maskY = (int)Math.floor(Math.random()*(maxPlayerY-minPlayerY))+minPlayerY;
        }
        canvas.drawBitmap(mask, maskX,maskY,null);

        //Levels Up
        int level = (int)Math.floor(scorer/50)+1;  //sets the levels based ona loop, for every 50 pts increase the difficulty and the level

        //Add Corona every level
        /**
         * array list for the class corona object
         * parses the objects from a vector
         * therefore many instances from one class
         */
        //add more corona based on level
        if(coronaObjectList.size() < level){
            for(int i = coronaObjectList.size(); i< level; i++) {
                int x = canvasWidth + 20;
                int y = (int) Math.floor(Math.random() * (maxPlayerY - minPlayerY)) + minPlayerY;
                //  CoronaObject obj = CoronaObject(getContext(),x,y);
                CoronaObject coronaObject = new CoronaObject(this.getContext(),x,y);
                coronaObjectList.add(coronaObject);
            }
        }
        /**
         * for loop that calculates the movement for the crona object vector
         */
        for(int i= 0; i < coronaObjectList.size(); i++){
            CoronaObject coronaObject = coronaObjectList.get(i);
            coronaObject.moveCorona(20);

            if (collisionCheck(coronaObject.getxCoronaPos(),coronaObject.getyCoronaPos())){ //collison check between the obstacles and corona and the player
                life_count --;
                soundsBar.playHitCoronaSound(); //play the sound effect

                if(life_count == 0){ //game lives
                    //Print Game Over
                    if(scorer > highScore){ //high score calculation
                        //save score
                        scoreManager.saveScore(scorer);
                        //update highscore
                        highScore = scorer;
                    }
                    gameScene = GAME_OVER;
                    return;

                }
                //Remove from List
                coronaObjectList.remove(i);
                i--;
                continue;
            }
            if(coronaObject.getxCoronaPos() < 0){
                //remove from list
                coronaObjectList.remove(i);
                i--;
                continue;
            }
            coronaObject.draw(canvas); //instantiates the canvas
        }

        //Scores, shows the score
        canvas.drawText("Score: 0" + scorer, 35, 60, scorerPaint);

        //Levels, shws the level
        canvas.drawText("Level." + level, canvasWidth/2,60,levelsPaint);

        //Lives, shows the total number of lives
        /**
         * for loop shows the number of lives decreasing from 3 based on collisions with corona
         *
         */
        for(int i = 0; i<3; i++){
            int x = (int) (getWidth()-lives[0].getWidth()*1.5*(i+1));
            int y = 30;
            if(i < life_count){
                canvas.drawBitmap(lives[0],x,y,null);
            } else {
                canvas.drawBitmap(lives[1],x,y,null);
            }
        }

    }

    /**
     * draws the start page of the application
     * @param canvas
     * variables for x positions of all variables
     */
    public void drawStartPage(Canvas canvas){
        playerY = canvasHeight/2;
        maskX = -100;
        glovesX = -100;
        // coronaX = -100;
        sanitizerX = -100;
        scorer = 0;
        life_count = 3;

        canvas.drawText("High Score :" + highScore, canvasWidth/2-highScoreTitlePaint.getStrokeWidth()/2,imageButtonY+buttonStart.getHeight()*2,highScoreTitlePaint); //shows the highscore on game over

        canvas.drawBitmap(buttonStart, imageButtonX,imageButtonY,null);

    }
    /**
     *
     * @param canvas
     * shows the game over page
     */
    public void drawGameOverPage(Canvas canvas){

        canvas.drawText("Score : " + highScore, canvasWidth/2,canvasHeight/2,highScoreTitlePaint); //shows the score on the main gameplay page

        canvas.drawBitmap(buttonReturn,imageButtonX,imageButtonY,null);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean collisionCheck(int x, int y){
        if(playerX < x && x < (playerX + player[0].getWidth())&& playerY < y && y <(playerY + player[0].getHeight())){ //checks the colision between player and surroundings
            return true; //returns true
        }
        return false; //returns false
    }
    /**
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {  //touch event or tap
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            switch(gameScene){
                case START_GAME:
                    //check start button
                    if(pressStart(buttonStart, (int)event.getX(),(int)event.getY())){
                        gameScene = PLAY_GAME;

                    }
                    break;
                case PLAY_GAME:
                    touch_flg = true;
                    playerSpeed = -20;
                    break;
                case GAME_OVER:
                    if(pressStart(buttonReturn, (int)event.getX(), (int)event.getY())){
                        gameScene = START_GAME;
                    }

            }

        }
        return true;
    }

    /**
     *
     * @param button
     * @param x
     * @param y
     * @return
     */

    public boolean pressStart(Bitmap button, int x, int y){
        if(imageButtonX < x && x < imageButtonX +button.getWidth()&&
                imageButtonY < y && y < imageButtonY + button.getHeight()){
            return true;
        }
        return false;
    }
    public int getGameScene(){
        return gameScene;
    }
}
