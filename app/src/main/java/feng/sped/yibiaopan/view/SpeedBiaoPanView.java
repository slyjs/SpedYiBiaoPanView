package feng.sped.yibiaopan.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 表盘的demo
 *
 * @author fengdan
 *         create at 2016/1/18 17:48
 */
public class SpeedBiaoPanView extends View {
    private Paint longPaint = null;
    private Paint percentPaint = null;
    private Paint firePaint = null;
    private Paint textPaint = null;
    private Paint bitmapPaint = null;
    private int count = 37;
    private int longWidth = 30;
    private int shortWidth = 20;
    private int allLineColor = Color.rgb(175, 175, 175);
    private int textColor = Color.rgb(131, 131, 131);
    private int percentColor = Color.rgb(0, 174, 255);
    private int textSize = 32;
    private int paintWidth = 5;
    private int textNum = 0;
    private int percentCount = 0;
    private Bitmap needleBitmap;
    private float orientationDegree = 0;

    public SpeedBiaoPanView(Context context) {
        super(context);
        initView(context, null, 0);

    }

    public SpeedBiaoPanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    public SpeedBiaoPanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    public void setPercentCount(int count, float degress) {
        this.percentCount = count;
        this.orientationDegree = degress;
        postInvalidate();
    }

    public void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        longPaint = new Paint();
        longPaint.setColor(allLineColor);
        longPaint.setStrokeWidth(paintWidth);
        longPaint.setAntiAlias(true);

        percentPaint = new Paint();
        percentPaint.setColor(percentColor);
        percentPaint.setStrokeWidth(paintWidth);
        percentPaint.setAntiAlias(true);

        firePaint = new Paint();
        firePaint.setColor(percentColor);
        firePaint.setStrokeWidth(paintWidth);

        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(20, BlurMaskFilter.Blur.SOLID);
        firePaint.setMaskFilter(blurMaskFilter);
        firePaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);

        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);

        needleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_speedometer_needle);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int pointX = width / 2;
        int pointY = height / 2;

        if (textNum != 0) {
            textNum = 0;
        }
        int widthBitmap = needleBitmap.getWidth();
        int heightBitmap = needleBitmap.getHeight();
        //利用矩阵旋转图片
        Matrix m = new Matrix();
        m.setTranslate(pointX - widthBitmap / 2, pointY - 20);
        m.preRotate(orientationDegree, widthBitmap / 2, 45);
        canvas.drawBitmap(needleBitmap, m, bitmapPaint);

        float degrees = (float) (270.0 / count);
        canvas.save();
        canvas.translate(0, pointY);
        canvas.rotate(-90, pointX, 0);

        for (int x = 1; x <= count; x++) {
            if (x % 4 == 1) {
                canvas.drawText(String.valueOf(textNum), longWidth + 10, 8, textPaint);
                textNum = textNum + 20;
                canvas.drawLine(0, 0, longWidth, 0, longPaint);
                canvas.rotate(degrees, pointX, 0);
            } else {
                canvas.drawLine(0, 0, shortWidth, 0, longPaint);
                canvas.rotate(degrees, pointX, 0);
            }
        }
        canvas.restore();

        canvas.save();
        canvas.translate(0, pointY);
        canvas.rotate(-90, pointX, 0);
        for (int x = 1; x <= percentCount; x++) {
            if (x == percentCount && x % 4 == 1) {
                canvas.drawLine(0, 0, longWidth, 0, firePaint);
                canvas.rotate(degrees, pointX, 0);
            } else if (x == percentCount && x % 4 != 1) {
                canvas.drawLine(0, 0, shortWidth, 0, firePaint);
                canvas.rotate(degrees, pointX, 0);
            } else if (x % 4 == 1) {
                canvas.drawLine(0, 0, longWidth, 0, percentPaint);
                canvas.rotate(degrees, pointX, 0);
            } else {
                canvas.drawLine(0, 0, shortWidth, 0, percentPaint);
                canvas.rotate(degrees, pointX, 0);
            }
        }
        canvas.restore();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
