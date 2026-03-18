package com.knightfiury.slidable;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CircularBeforeAfterView extends View {

    private Bitmap beforeImage;
    private Bitmap afterImage;
    private float dividerPosition = 0.5f;
    private Paint dividerPaint;
    private Paint knobPaint;
    private Path circlePath;
    private float knobRadius = 20f;

    public CircularBeforeAfterView(Context context) {
        super(context);
        init();
    }

    public CircularBeforeAfterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        loadAttributes(context, attrs);
    }

    private void init() {
        dividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dividerPaint.setColor(Color.WHITE);
        dividerPaint.setStrokeWidth(5f);

        knobPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        knobPaint.setColor(Color.WHITE);
        knobPaint.setStyle(Paint.Style.FILL);

        circlePath = new Path();
    }

    private void loadAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircularBeforeAfterView);
        int beforeRes = a.getResourceId(R.styleable.CircularBeforeAfterView_beforeImage, -1);
        int afterRes = a.getResourceId(R.styleable.CircularBeforeAfterView_afterImage, -1);
        int dividerColor = a.getColor(R.styleable.CircularBeforeAfterView_dividerColor, Color.WHITE);
        float dividerWidth = a.getDimension(R.styleable.CircularBeforeAfterView_dividerWidth, 5f);
        int knobColor = a.getColor(R.styleable.CircularBeforeAfterView_knobColor, Color.WHITE);
        float knobRadiusDp = a.getDimension(R.styleable.CircularBeforeAfterView_knobRadius, 20f);
        float initialPos = a.getFloat(R.styleable.CircularBeforeAfterView_initialPosition, 0.5f);
        a.recycle();

        if (beforeRes != -1) beforeImage = BitmapFactory.decodeResource(getResources(), beforeRes);
        if (afterRes != -1) afterImage = BitmapFactory.decodeResource(getResources(), afterRes);
        dividerPaint.setColor(dividerColor);
        dividerPaint.setStrokeWidth(dividerWidth);
        knobPaint.setColor(knobColor);
        knobRadius = knobRadiusDp;
        dividerPosition = initialPos;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (beforeImage == null || afterImage == null) return;

        Bitmap beforeScaled = Bitmap.createScaledBitmap(beforeImage, getWidth(), getHeight(), true);
        Bitmap afterScaled = Bitmap.createScaledBitmap(afterImage, getWidth(), getHeight(), true);

        float radius = Math.min(getWidth(), getHeight()) / 2f;
        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;

        circlePath.reset();
        circlePath.addCircle(cx, cy, radius, Path.Direction.CW);
        canvas.clipPath(circlePath);

        canvas.drawBitmap(beforeScaled, 0, 0, null);

        Rect clip = new Rect(0, 0, (int) (getWidth() * dividerPosition), getHeight());
        canvas.save();
        canvas.clipRect(clip);
        canvas.drawBitmap(afterScaled, 0, 0, null);
        canvas.restore();

        float x = getWidth() * dividerPosition;
        canvas.drawLine(x, cy - radius, x, cy + radius, dividerPaint);
        canvas.drawCircle(x, cy, knobRadius, knobPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dividerPosition = event.getX() / getWidth();
        if (dividerPosition < 0) dividerPosition = 0;
        if (dividerPosition > 1) dividerPosition = 1;
        invalidate();
        return true;
    }

    /** Animate the knob from any start to any end position (0 to 1 range) **/
	public void move(float start, float end) {
		// clamp values between 0 and 1
		start = Math.max(0, Math.min(1, start));
		end = Math.max(0, Math.min(1, end));
		
		ValueAnimator animator = ValueAnimator.ofFloat(start, end);
		animator.setDuration(1500); // configurable duration
		
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				dividerPosition = (Float) animation.getAnimatedValue();
				invalidate();
			}
		});
		
		animator.start();
	} 
}
