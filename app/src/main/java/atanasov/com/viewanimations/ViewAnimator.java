package atanasov.com.viewanimations;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

import java.io.InvalidClassException;

public class ViewAnimator {
    private View mView;
    private int mStartDelay = 0;
    private int mDuration = 0;
    private Interpolator mInterpolator;

    public ViewAnimator setView(View mView) {
        this.mView = mView;

        return this;
    }

    public ViewAnimator setStartDelay(int mStartDelay) {
        this.mStartDelay = mStartDelay;

        return this;
    }

    public ViewAnimator setDuration(int mDuration) {
        this.mDuration = mDuration;

        return this;
    }

    public ViewAnimator setInterpolator(Interpolator mInterpolator) {
        this.mInterpolator = mInterpolator;

        return this;
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

    public void animateLeftPadding(final int newPadding) {
        if (mView == null)
            throw new NullPointerException("View is null");

        if (mInterpolator == null)
            mInterpolator = new AccelerateInterpolator();

        ValueAnimator animator = ValueAnimator.ofInt(mView.getPaddingLeft(), newPadding);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mView.setPadding((Integer) valueAnimator.getAnimatedValue(), mView.getPaddingTop(), mView.getPaddingRight(), mView.getPaddingBottom());
                mView.requestLayout();
            }
        });

        animator.setDuration(mDuration);
        animator.setStartDelay(mStartDelay);
        animator.setInterpolator(mInterpolator);
        animator.start();
    }

    public void animateTextColor(int newColor) throws InvalidClassException {
        if (mView == null)
            throw new NullPointerException("View is null");

        if (!(mView instanceof TextView))
            throw new InvalidClassException("Not a TextView");

        final TextView v = (TextView) mView;

        ValueAnimator animator = ValueAnimator.ofInt(v.getCurrentTextColor(), newColor);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.setTextColor((int) animation.getAnimatedValue());
            }
        });

        animator.setDuration(mDuration);
        animator.setStartDelay(mStartDelay);
        animator.setInterpolator(mInterpolator);
        animator.start();
    }

    public void animateOpacity(float newOpacity) {
        if (mView == null)
            throw new NullPointerException("View is null");

        final ValueAnimator animator = ValueAnimator.ofFloat(mView.getAlpha(), newOpacity);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mView.setAlpha((float) animator.getAnimatedValue());
            }
        });

        animator.setDuration(mDuration);
        animator.setStartDelay(mStartDelay);
        animator.setInterpolator(mInterpolator);
        animator.start();
    }
}
