package atanasov.com.viewanimations;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

public class ViewAnimator {
    private View mView;
    private int mStartDelay = 0;
    private int mDuration = 0;
    private Interpolator mInterpolator;

    public void setView(View mView) {
        this.mView = mView;
    }

    public void setStartDelay(int mStartDelay) {
        this.mStartDelay = mStartDelay;
    }

    public void setDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public void setInterpolator(Interpolator mInterpolator) {
        this.mInterpolator = mInterpolator;
    }

    public void animateLeftMargin(final int newMargin) {
        if (mView == null)
            throw new NullPointerException("View is null");

        if (mInterpolator == null)
            mInterpolator = new AccelerateInterpolator();

        final ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(params.leftMargin, newMargin);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                params.leftMargin = (Integer) valueAnimator.getAnimatedValue();
                mView.requestLayout();
            }
        });

        animator.setDuration(mDuration);
        animator.setStartDelay(mStartDelay);
        animator.setInterpolator(mInterpolator);
        animator.start();
    }

    public void animateRightMargin(final int newMargin) {
        if (mView == null)
            throw new NullPointerException("View is null");

        if (mInterpolator == null)
            mInterpolator = new AccelerateInterpolator();

        final ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(params.rightMargin, newMargin);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                params.rightMargin = (Integer) valueAnimator.getAnimatedValue();
                mView.requestLayout();
            }
        });

        animator.setDuration(mDuration);
        animator.setStartDelay(mStartDelay);
        animator.setInterpolator(mInterpolator);
        animator.start();
    }
}
