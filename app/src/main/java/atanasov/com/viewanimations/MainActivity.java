package atanasov.com.viewanimations;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity implements SwipeInterface {
    @InjectView(R.id.container)
    RelativeLayout container;
    @InjectView(R.id.line)
    View line;

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
        ViewAnimator animator = new ViewAnimator();
        animator.setView(line);
        animator.setDuration(600);

        animator.animateLeftMargin(500);
    }

    @Override
    public void onRightToLeft(View v) {
        ViewAnimator animator = new ViewAnimator();
        animator.setView(line);
        animator.setDuration(600);

        animator.animateLeftMargin(0);
    }
}
