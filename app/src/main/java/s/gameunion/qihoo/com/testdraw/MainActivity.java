package s.gameunion.qihoo.com.testdraw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import s.gameunion.qihoo.com.testdraw.view.ProgressTextBar;

public class MainActivity extends AppCompatActivity {

    private ProgressTextBar mProgressTextBar;
    private int mProgress = 0;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mProgress++;
            if (mProgress == 100){
                mProgress = 0;
                mProgressTextBar.setProgress(100);
                return;
            }
            mProgressTextBar.setProgress(mProgress);
            mProgressTextBar.postDelayed(runnable,100);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressTextBar = findViewById(R.id.progress_text_bar);
        mProgressTextBar.setMax(100);
        mProgressTextBar.postDelayed(runnable,100);
    }
}
