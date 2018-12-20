package s.gameunion.qihoo.com.testdraw.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import s.gameunion.qihoo.com.testdraw.utils.Utils;

/**
 * 进度条文字变色
 * Created by jiangdongbo on 2018/12/20.
 */
public class ProgressTextBar extends ProgressBar {

    private static final String TAG = ProgressTextBar.class.getSimpleName();

    private PorterDuffXfermode mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    private Paint mTextPaint;//文字画笔

    private int mWidth,mProgress = 0,mMax = 1,mTextSize = 0;
    private boolean mIsInit;

    public ProgressTextBar(Context context) {
        this(context, null, 0);
    }

    public ProgressTextBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressTextBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        mTextSize = Utils.dp2px(getContext(),20);
        mTextPaint.setTextSize(mTextSize);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //画文字
        float present = (float)mProgress / (float)mMax;
        mWidth = (int)(getWidth() * present);//进度作一下处理，解决不同分辨率的适配问题
        String presentStr = (int)(present * 100) + "%";
        Rect percentRect = new Rect();
        mTextPaint.getTextBounds(presentStr, 0, presentStr.length(), percentRect);//为了获取文字的宽高以及坐标位置，get之后，rect.centerX才有值
        int percentX = (getWidth() / 2) - percentRect.centerX();//获取百分比文字的中心横坐标
        int percentY = (getHeight() / 2) - percentRect.centerY();

        Bitmap srcBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas  srcCanvas = new Canvas(srcBitmap);
        canvas.drawText(presentStr,percentX,percentY,mTextPaint);
        srcCanvas.drawText(presentStr,percentX,percentY,mTextPaint);
        // 设置混合模式
        mTextPaint.setXfermode(mPorterDuffXfermode);
        mTextPaint.setColor(Color.BLACK);
        RectF rectF = new RectF(0, 0, mWidth, getHeight());//mWidth是不断变化的
        // 绘制源图形
        srcCanvas.drawRoundRect(rectF,getHeight() / 2,getHeight() / 2, mTextPaint);
        // 绘制目标图
        canvas.drawBitmap(srcBitmap, 0, 0, null);
        // 清除混合模式
        mTextPaint.setXfermode(null);
        mTextPaint.setColor(Color.WHITE);

    }

    public void setProgress(int progress){
        mProgress = progress;
        super.setProgress(progress);
    }

    public void setMax(int max){
        mMax = max;
        super.setMax(max);
    }
}
