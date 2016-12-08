package se.antonfredriksson.mathrendering;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.List;

/**
 * Created by a95 on 2016-12-06.
 */

public class Superscript implements Token {

    final double SUPERSCRIPTFACTOR = 0.75;

    List<Token> childs;
    double width=0;
    public Superscript(List<Token> childs) {
        this.childs = childs;
    }

    @Override
    public double getHeight() {
        double h=0;
        for (int i = 0; i < childs.size(); i++) {
            h += childs.get(i).getHeight()*SUPERSCRIPTFACTOR;
        }
        return h+1*SUPERSCRIPTFACTOR;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double Render(Point loc, Paint p, Canvas c) {
        Point _loc = new Point(loc.x,loc.y);
        _loc.offset(5,(int)-p.getTextSize()/2);
        Paint _p = new Paint();
        _p.set(p);
        _p.setTextSize((float)(_p.getTextSize()*SUPERSCRIPTFACTOR));
        for (Token ch: childs) {
            ch.Render(_loc,_p,c);
            _loc.offset((int)ch.getWidth(),0);
        }
        width = _loc.x-loc.x;
        return _loc.x-loc.x+5;
    }
}
