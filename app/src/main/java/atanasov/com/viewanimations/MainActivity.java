package atanasov.com.viewanimations;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity implements SwipeInterface {
    @InjectView(R.id.container)
    RelativeLayout container;
    @InjectView(R.id.line)
    View line;

    private static final int ANIMATION_DURATION = 300;

    ActivitySwipeDetector swipeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        swipeDetector = new ActivitySwipeDetector(getBaseContext(), MainActivity.this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        swipeDetector.onTouch(container, me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onLeftToRight(View v) {
        new ViewAnimator()
                .setView(line)
                .setDuration(300)
                .setInterpolator(new OvershootInterpolator())
                .animateLeftMargin(500);
    }

    @Override
    public void onRightToLeft(View v) {
        new ViewAnimator()
                .setView(line)
                .setDuration(300)
                .setInterpolator(new OvershootInterpolator())
                .animateLeftMargin(0);
    }
}
