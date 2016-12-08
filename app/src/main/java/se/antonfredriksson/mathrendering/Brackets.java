package se.antonfredriksson.mathrendering;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a95 on 2016-12-07.
 */

public class Brackets implements Token {

    List<Token> childs;

    public Brackets(List<Token> childs) {
        this.childs = childs;
    }

    public Brackets() {
        this.childs = new ArrayList<>();
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
        double maxH = 0;
        Point _loc = new Point(loc);
        Rect r = new Rect();
        p.getTextBounds("(",0,1,r);
        _loc.offset((int)r.width(),0);

        for (Token ch: childs) {
            ch.Render(_loc,p,c);
            _loc.offset((int)ch.getWidth(),0);
            maxH = Math.max(maxH,ch.getHeight());
        }

        if (Math.floor(maxH) == 1){

        }else{
            c.drawText(TOPPAR,loc.x,loc.y-64,p);
            c.drawText(MIDPAR,loc.x,loc.y+128/2,p);
            c.drawText(BOTPAR,loc.x,loc.y+128+64,p);

            c.drawText(RTOPPAR,_loc.x,loc.y-64,p);
            c.drawText(RMIDPAR,_loc.x,loc.y+128/2,p);
            c.drawText(RBOTPAR,_loc.x,loc.y+128+64,p);
        }

        return (_loc.x-loc.x)+r.width();
    }

    final String TOPPAR = 	"\u239B";
    final String MIDPAR = 	"\u239C";
    final String BOTPAR = 	"\u239D";

    final String RTOPPAR = 	"\u239E";
    final String RMIDPAR = 		"\u239F";
    final String RBOTPAR = 	"\u23A0";
}
