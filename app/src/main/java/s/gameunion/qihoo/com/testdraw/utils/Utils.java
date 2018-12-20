package s.gameunion.qihoo.com.testdraw.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by jiangdongbo on 2018/12/20.
 */
public class Utils {

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
