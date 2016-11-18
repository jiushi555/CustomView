package xml.org.customtimepiece;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/8/25.
 */
public class CustomView extends View {
    private float mCircleXY, mLength, mRadius;
    private Paint mPaint1 = new Paint();   //表圆弧
    private Paint mPaint2 = new Paint();   //表刻度
    private Paint mPaint3 = new Paint();   //表时针
    private Paint mPaint4 = new Paint();   //表秒针
    private WindowManager mWM;
    private int time = 0;
    private int hour = 0;

    public CustomView(Context context) {
        super(context);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(5);
        mPaint1.setAntiAlias(false);
        mPaint3.setStrokeWidth(20);
        mPaint4.setStrokeWidth(10);
        setValues();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(5);
        mPaint1.setAntiAlias(false);
        mPaint3.setStrokeWidth(20);
        mPaint4.setStrokeWidth(10);
        setValues();
    }

    private void setValues() {
        mWM = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        mLength = mWM.getDefaultDisplay().getWidth();
        mCircleXY = mLength / 2;
        mRadius = (float) (mLength * 0.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制钟表外圈圆环
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius-20, mPaint1);
        //消除锯齿
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG));
        for (int i = 0; i < 60; i++) {
            //通过旋转坐标系绘制刻度
            String degree = String.valueOf(i / 5);
            if (i % 15 == 0) {
                mPaint2.setStrokeWidth(5);
                mPaint2.setTextSize(30);
                canvas.drawLine(mCircleXY, 20, mCircleXY, 80, mPaint2);
                if (i == 0) {
                    canvas.drawText("12", mCircleXY - mPaint2.measureText(degree) / 2, 120, mPaint2);
                } else {
                    canvas.drawText(degree, mCircleXY - mPaint2.measureText(degree) / 2, 120, mPaint2);
                }
            } else if (i % 5 == 0) {
                mPaint2.setStrokeWidth(5);
                mPaint2.setTextSize(30);
                canvas.drawLine(mCircleXY, 20, mCircleXY,60, mPaint2);
                canvas.drawText(degree, mCircleXY - mPaint2.measureText(degree) / 2, 100, mPaint2);
            } else {
                mPaint2.setStrokeWidth(5);
                mPaint2.setTextSize(30);
                canvas.drawLine(mCircleXY, 20, mCircleXY, 40, mPaint2);
            }
            canvas.rotate(6, mCircleXY, mCircleXY);
        }

        //平移坐标系，绘制时针
        canvas.translate(mCircleXY, mCircleXY);
        canvas.drawLine(0, 0, 100, 100, mPaint3);

        //旋转坐标系，绘制分针
        canvas.rotate(6 * time, 0, 0);
        canvas.drawLine(0, 0, 100, 200, mPaint4);

        time++;
        postInvalidateDelayed(1000);
    }

}
