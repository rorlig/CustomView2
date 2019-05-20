package com.example.customview2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class EmojiView extends View {

    private final Context context;
    // Paint object for coloring and styling
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // Some colors for the face background, eyes and mouth.
    private int faceColor = Color.YELLOW;
    private int eyesColor = Color.BLACK;
    private int mouthColor = Color.BLACK;
    private int borderColor = Color.BLACK;
    // Face border width in pixels
    private float borderWidth = 4.0f;
    // View size in pixels
    private int size = 320;

    public EmojiView(Context context) {
        super(context);
        this.context = context;
    }

    public EmojiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray
                = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.EmojiView,
                0, 0);
            int happinessState = typedArray.getInt(R.styleable.EmojiView_state, 0);
            faceColor = typedArray.getColor(R.styleable.EmojiView_faceColor, Color.YELLOW);
            eyesColor = typedArray.getColor(R.styleable.EmojiView_eyesColor, Color.WHITE);
            mouthColor = typedArray.getColor(R.styleable.EmojiView_mouthColor, Color.BLACK);
            borderColor = typedArray.getColor(R.styleable.EmojiView_borderColor,
                    Color.BLACK);
            borderWidth = typedArray.getDimension(R.styleable.EmojiView_borderWidth,
                    1);
            typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawFace(canvas);
        drawEyes(canvas);
        drawPath(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        size = Math.min(getMeasuredHeight(), getMeasuredWidth());
        setMeasuredDimension(size, size);
    }

    private void drawFace(Canvas canvas) {
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);
        float radius = size / 2f;
        canvas.drawCircle(size / 2f, size / 2f, radius, paint);
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint);
    }


    private void drawEyes(Canvas canvas) {
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);
        RectF leftEyeRect = new RectF(size * 0.32f, size * 0.23f,
                size * 0.43f, size * 0.50f);

        canvas.drawOval(leftEyeRect, paint);
        RectF rightEyeRect = new RectF(size * 0.57f, size * 0.23f,
                                    size * 0.68f, size * 0.50f);
        canvas.drawOval(rightEyeRect, paint);
    }

    private void drawPath(Canvas canvas) {
        Path mouthPath = new Path();
        mouthPath.moveTo(size * 0.22f, size * 0.7f);
        mouthPath.quadTo(size * 0.50f, size * 0.80f,
                size * 0.78f, size * 0.70f);
        mouthPath.quadTo(size * 0.50f, size * 0.90f,
                size * 0.22f, size * 0.70f);
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mouthPath, paint);
    }
}

