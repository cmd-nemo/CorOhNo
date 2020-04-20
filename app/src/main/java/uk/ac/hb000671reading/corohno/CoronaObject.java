package uk.ac.hb000671reading.corohno;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Corona Object class for parsing the respective parameters of the obstacle corona
 */
public class CoronaObject {
    private BitmapFactory bm = new BitmapFactory(); //sets bitmap
    private int xCoronaPos; //sets the xpos of corona
    private int yCoronaPos; //sets the y pos of the corona
    private final Bitmap coronaObject; //shows the final bitmap

    public CoronaObject(Context context, int x, int y) {
        //create new Corona Object
        xCoronaPos =  x; //declares the context and x,y value
        yCoronaPos =  y;
        coronaObject = BitmapFactory.decodeResource(context.getResources(),R.drawable.covid); //drawable of corona
    }

    /**
     *
     * @return xCoronaPos
     */
    public int getxCoronaPos() {
        return xCoronaPos; //gets the x pos
    }

    /**
     *
     * @return yCoronaPos
     */
    public int getyCoronaPos(){
        return yCoronaPos; //gets the ypos
    }

    /**
     *
     * @param speed
     */
    public void moveCorona(int speed){
        xCoronaPos -= speed; //gets the speed
    }

    /**
     *
     * @param canvas
     */
    public void draw (Canvas canvas){
        Log.e("CObject","x = "+xCoronaPos+" and y = "+yCoronaPos); //draws the canvas based on bitmap coronaobject
        canvas.drawBitmap(coronaObject,xCoronaPos,yCoronaPos,null);
    }
}
