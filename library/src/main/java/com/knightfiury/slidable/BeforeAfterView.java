package com.knightfiury.slidable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BeforeAfterView extends View {

    private Bitmap beforeImage;
    private Bitmap afterImage;
    private float dividerPosition = 0.5f;
    private Paint dividerPaint;
    private Paint knobPaint;
    private float knobRadius = 20f;

    public BeforeAfterView(Context context) {
        super(context);
        init();
    }

    public BeforeAfterView(Context context, AttributeSet attrs) {
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
    }

    private void loadAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BeforeAfterView);
        int beforeRes = a.getResourceId(R.styleable.BeforeAfterView_beforeImage, -1);
        int afterRes = a.getResourceId(R.styleable.BeforeAfterView_afterImage, -1);
        int dividerColor = a.getColor(R.styleable.BeforeAfterView_dividerColor, Color.WHITE);
        float dividerWidth = a.getDimension(R.styleable.BeforeAfterView_dividerWidth, 5f);
        int knobColor = a.getColor(R.styleable.BeforeAfterView_knobColor, Color.WHITE);
        float knobRadiusDp = a.getDimension(R.styleable.BeforeAfterView_knobRadius, 20f);
        float initialPos = a.getFloat(R.styleable.BeforeAfterView_initialPosition, 0.5f);
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

        canvas.drawBitmap(beforeScaled, 0, 0, null);

        Rect clip = new Rect(0, 0, (int) (getWidth() * dividerPosition), getHeight());
        canvas.save();
        canvas.clipRect(clip);
        canvas.drawBitmap(afterScaled, 0, 0, null);
        canvas.restore();
        
        float x = getWidth() * dividerPosition;
        canvas.drawLine(x, 0, x, getHeight(), dividerPaint);
        canvas.drawCircle(x, getHeight() / 2f, knobRadius, knobPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dividerPosition = event.getX() / getWidth();
        if (dividerPosition < 0) dividerPosition = 0;
        if (dividerPosition > 1) dividerPosition = 1;
        invalidate();
        return true;
    }
}
