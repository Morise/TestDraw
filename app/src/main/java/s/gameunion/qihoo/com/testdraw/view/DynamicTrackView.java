package s.gameunion.qihoo.com.testdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import s.gameunion.qihoo.com.testdraw.utils.Utils;

/**
 * 动态音轨
 * Created by jiangdongbo on 2018/12/14.
 */
public class DynamicTrackView extends RelativeLayout {

    private static final String TAG = DynamicTrackView.class.getSimpleName();

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mRectWidth;
    private int mRectHeight1;
    private int mRectHeight2;
    private int mRectHeight3;
    private int mRectHeight4;

    private int mRectTop1;
    private int mRectTop2;
    private int mRectTop3;
    private int mRectTop4;

    private boolean mToTop1 = false;
    private boolean mToTop2 = false;
    private boolean mToTop3 = false;
    private boolean mToTop4 = false;

    public DynamicTrackView(Context context) {
        this(context, null, 0);
    }

    public DynamicTrackView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DynamicTrackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);

        mWidth = Utils.dp2px(getContext(), 16);
        mHeight = Utils.dp2px(getContext(), 14);
        mRectWidth = Utils.dp2px(getContext(), 2);
        mRectHeight1 = Utils.dp2px(getContext(), 8);
        mRectHeight2 = Utils.dp2px(getContext(), 14);
        mRectHeight3 = Utils.dp2px(getContext(), 11);
        mRectHeight4 = Utils.dp2px(getContext(), 5);

        mRectTop1 = mHeight - mRectHeight1;
        mRectTop2 = mHeight - mRectHeight2;
        mRectTop3 = mHeight - mRectHeight3;
        mRectTop4 = mHeight - mRectHeight4;

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);

        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //第一个
        int left1 = Utils.dp2px(getContext(), 1);
        if (mToTop1) {
            mRectTop1++;
            Log.v(TAG, "top1++ = " + mRectTop1);
            if (mRectTop1 > Utils.dp2px(getContext(), 12)) {
                mToTop1 = false;
            }
        } else {
            mRectTop1--;
            Log.v(TAG, "top1-- = " + mRectTop1);
            if (mRectTop1 < Utils.dp2px(getContext(), 9)) {
                mToTop1 = true;
            }
        }
        RectF rect1 = new RectF(left1, mRectTop1, mRectWidth + left1, mHeight);
        canvas.drawRoundRect(rect1, Utils.dp2px(getContext(), 1), Utils.dp2px(getContext(), 1), mPaint);

        //第二个
        int left2 = Utils.dp2px(getContext(), 5);
        if (mToTop2) {
            mRectTop2 += 2;
            if (mRectTop2 > Utils.dp2px(getContext(), 12)) {
                mToTop2 = false;
            }
        } else {
            mRectTop2 -= 2;
            if (mRectTop2 < Utils.dp2px(getContext(), 2)) {
                mToTop2 = true;
            }
        }
        RectF rect2 = new RectF(left2, mRectTop2, mRectWidth + left2, mHeight);
        canvas.drawRoundRect(rect2, Utils.dp2px(getContext(), 1), Utils.dp2px(getContext(),1), mPaint);

        //第三个
        int left3 = Utils.dp2px(getContext(), 9);
        if (mToTop3) {
            mRectTop3++;
            if (mRectTop3 > Utils.dp2px(getContext(), 12)) {
                mToTop3 = false;
            }
        } else {
            mRectTop3--;
            if (mRectTop3 < Utils.dp2px(getContext(), 2)) {
                mToTop3 = true;
            }
        }
        RectF rect3 = new RectF(left3, mRectTop3, mRectWidth + left3, mHeight);
        canvas.drawRoundRect(rect3, Utils.dp2px(getContext(), 1), Utils.dp2px(getContext(),1), mPaint);

        //第四个
        int left4 = Utils.dp2px(getContext(), 13);
        if (mToTop4) {
            mRectTop4++;
            if (mRectTop4 > Utils.dp2px(getContext(), 12)) {
                mToTop4 = false;
            }
        } else {
            mRectTop4--;
            if (mRectTop4 < Utils.dp2px(getContext(), 2)) {
                mToTop4 = true;
            }
        }
        RectF rect4 = new RectF(left4, mRectTop4, mRectWidth + left4, mHeight);
        canvas.drawRoundRect(rect4, Utils.dp2px(getContext(), 1), Utils.dp2px(getContext(),1), mPaint);

        invalidate();
    }


}
