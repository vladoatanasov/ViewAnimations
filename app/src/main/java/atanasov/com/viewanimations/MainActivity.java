package atanasov.com.viewanimations;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InvalidClassException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity implements SwipeInterface {
    @InjectView(R.id.container)
    RelativeLayout container;
    @InjectView(R.id.line)
    View line;
    @InjectView(R.id.label)
    TextView label;

    private static final int ANIMATION_DURATION = 300;

    ActivitySwipeDetector swipeDetector;

    private boolean animatedColor = false;
    private boolean animatedOpacity = false;

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
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(new OvershootInterpolator())
                .animateLeftMargin(0);
    }

    @OnClick(R.id.animate_color_btn)
    void onAnimateColorClick() {
        String animateToColor;

        if (!animatedColor)
            animateToColor = "#FFFF1844";
        else
            animateToColor = "#fffff42b";

        try {
            new ViewAnimator()
                    .setView(label)
                    .setDuration(ANIMATION_DURATION)
                    .animateTextColor(Color.parseColor(animateToColor));

            animatedColor = !animatedColor;
        } catch (InvalidClassException e) {
            Log.e("ViewAnimator", e.getMessage());
        }
    }

    @OnClick(R.id.animate_opacity_btn)
    void onAnimateOpacityClick() {
        float animateToOpacity;

        if (!animatedOpacity)
            animateToOpacity = 0.6f;
        else
            animateToOpacity = 1f;

        new ViewAnimator()
                .setView(label)
                .setDuration(ANIMATION_DURATION)
                .animateOpacity(animateToOpacity);

        animatedOpacity = !animatedOpacity;
    }

}
