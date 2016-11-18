package xml.org.customview1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

/**
 * Created by Administrator on 2016/8/23.
 */
public class CumtomView extends View {

    private int mRectCount=10,mWidth=100,offset=20;
    private float currentHeight,mRectWidth=600,mRectHeight=50;
    private Paint mPaint1=new Paint();
    private Paint mPaint2=new Paint();
    private LinearGradient mLinearGradient;
    private TypedArray ta;
    private int mMainColor,mRectColor1,mRectColor2;
    public CumtomView(Context context) {
        super(context);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2.setStyle(Paint.Style.FILL);
        ta=context.obtainStyledAttributes(R.styleable.CumtomView1);
        getValues();
        mPaint2.setColor(mMainColor);
    }
    public CumtomView(Context context,AttributeSet attributeSet){
        super(context, attributeSet);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2.setStyle(Paint.Style.FILL);
        ta=context.obtainStyledAttributes(attributeSet, R.styleable.CumtomView1);
        getValues();
        mPaint2.setColor(mMainColor);
    }

    private void getValues() {
        mMainColor = ta.getInt(R.styleable.CumtomView1_MainBackGround, 0);
        mRectColor1 = ta.getInt(R.styleable.CumtomView1_RectBackGround1, 0);
        mRectColor2 = ta.getInt(R.styleable.CumtomView1_RectBackGround2,0);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(20, 0, getMeasuredWidth() - 20, 800, mPaint2);

        for(int i=0;i<mRectCount;i++){
            double mRandom=Math.random();
            currentHeight=(float)(mRectHeight*mRandom);
            canvas.drawRect(
                    (float)(mWidth*0.4/2+mRectWidth*i+offset),
                    (float)currentHeight,
                    (float)(mWidth*0.4/2+mRectWidth*(i+1)),
                    (float)800,
                    mPaint1);
        }
        postInvalidateDelayed(300);
    }

    /**
     * 绘制矩形长度的颜色渐变
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=getWidth();
        mRectHeight=getHeight();
        mRectWidth=(int)(mWidth*0.6/mRectCount);
        mLinearGradient=new LinearGradient(
                0,
                0,
                mRectWidth,
                mRectHeight,
                mRectColor1,
                mRectColor2,
                Shader.TileMode.CLAMP);
        mPaint1.setShader(mLinearGradient);
    }
}
