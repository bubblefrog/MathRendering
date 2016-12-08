package se.antonfredriksson.mathrendering;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a95 on 2016-12-06.
 */

public class Division implements Token {

    final double SUPERSCRIPTFACTOR = 0.75;
    List<Token> top;
    List<Token> bot;

    public Division(List<Token> top, List<Token> bot) {
        this.top = top;
        this.bot = bot;
    }

    public Division() {
        top = new ArrayList<>();
        bot = new ArrayList<>();
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double Render(Point loc, Paint p, Canvas c) {

        Log.d("DivP",loc.toString());
        Point _loc = new Point(loc.x,loc.y);
        _loc.offset(5,(int)-p.getTextSize()/2);
        Paint _p = new Paint();
        _p.set(p);
        _p.setTextSize((float)(_p.getTextSize()*SUPERSCRIPTFACTOR));
        for (Token ch: top) {
            ch.Render(_loc,_p,c);
            _loc.offset((int)ch.getWidth(),0);
        }

        _p.setStrokeWidth(5);
        c.drawLine(loc.x+5,loc.y,_loc.x+5,loc.y,_p);

        _loc = new Point(loc.x,(int)(loc.y+p.getTextSize()/2)-15);
        _loc.offset(5,15);
        _p = new Paint();
        _p.set(p);
        _p.setTextSize((float)(_p.getTextSize()*SUPERSCRIPTFACTOR));
        for (Token ch: bot) {
            ch.Render(_loc,_p,c);
            _loc.offset((int)ch.getWidth(),0);
        }

        return _loc.x-loc.x+10;
    }
}
