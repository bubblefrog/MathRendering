package se.antonfredriksson.mathrendering;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by a95 on 2016-12-06.
 */

public class Text implements Token {
    String data;
    double width=0;
    public Text(String data) {
        this.data = data;
    }

    @Override
    public double getHeight() {
        return 1;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double Render(Point loc, Paint p, Canvas c) {
        Log.d("TextP",loc.toString());
        Rect b = new Rect();

        p.getTextBounds(data,0,data.length(),b);
        Log.d("TextH",b.height() + "");
        c.drawText(data,loc.x,loc.y+b.height()/2,p);
        width = b.width();
        return b.width()+10;
    }
}
