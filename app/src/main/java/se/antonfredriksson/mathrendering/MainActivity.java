package se.antonfredriksson.mathrendering;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class MainActivity extends Activity implements SurfaceHolder.Callback{

    SurfaceView view;
    Paint p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new SurfaceView(this);
        view.getHolder().addCallback(this);
        p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setAntiAlias(true);
        p.setColor(Color.BLACK);
        p.setTextSize(128f);
        setContentView(view);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Canvas c = holder.lockCanvas();
        c.drawColor(Color.WHITE);

        /*c.drawText(TOPPAR,100,100,p);
        //c.drawText(MIDPAR,100,128+100,p);
        c.drawText(BOTPAR,100,1*128+100,p);
        c.drawText(" (2+7)",100,128,p);
        Rect bound = new Rect();*/

        ArrayList<Token> t = new ArrayList<>();

        Brackets b = new Brackets();
        b.childs.add(new Text("2+9"));

        Superscript s = getNewSuperScript(new Text("3"));
        s.childs.add(getNewSuperScript(new Text("8")));
        b.childs.add(s);
        t.add(b);
        t.add(new Text("×"));
        Division d = new Division();
        d.top.add(new Text("18"));
        d.top.add(getNewSuperScript(new Text("79+8")));
        d.bot.add(new Text("8"));
        t.add(d);
        Point po = new Point(100,300);
        for (Token a:t) {
            po.offset((int)a.Render(po,p,c),0);

            Log.d("Heigth",a.getHeight()+"");
        }


        holder.unlockCanvasAndPost(c);
    }

    Superscript getNewSuperScript(Token t){
        ArrayList<Token> asd = new ArrayList<>();
        asd.add(t);
        return (new Superscript(asd));
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }



}
