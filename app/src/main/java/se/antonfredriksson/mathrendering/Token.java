package se.antonfredriksson.mathrendering;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by a95 on 2016-12-06.
 */

public interface Token {
    double getHeight();
    double getWidth();

    double Render(Point loc, Paint p, Canvas c);
}
