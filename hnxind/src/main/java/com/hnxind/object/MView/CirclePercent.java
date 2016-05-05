package com.hnxind.object.MView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.hnxind.object.R;

/**
 * Created by Administrator on 2016/4/15.
 */
public class CirclePercent extends View {
    float MaxProgress;
    float Progress;
    int width;
    int Color;
    int BackColor;

    public float getMaxProgress() {
        return MaxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        MaxProgress = maxProgress;
    }

    public float getProgress() {
        return Progress;
    }

    public void setProgress(int progress) {
        Progress = progress;
        invalidate();
//        postInvalidate();
    }

    public float getPercent(){
        float percent=0;
        percent = 360*(getProgress()/getMaxProgress());
        Toast.makeText(getContext(),percent+"",Toast.LENGTH_SHORT).show();
        return percent;
    }

    public CirclePercent(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CirclePercent);
        MaxProgress = typedArray.getInteger(R.styleable.CirclePercent_MaxProgress,100);
        Progress = typedArray.getInteger(R.styleable.CirclePercent_Progress,0);
        Color = typedArray.getColor(R.styleable.CirclePercent_Color, android.graphics.Color.WHITE);
        BackColor = typedArray.getColor(R.styleable.CirclePercent_BackColor, android.graphics.Color.BLACK);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center=getWidth()/2;
        int width=getWidth()/2;
        Paint paint = new Paint();
        paint.setColor(BackColor);
        paint.setAntiAlias(true);
        canvas.drawCircle(center,center,center,paint);

        paint.setColor(Color);
        RectF rectF=new RectF();
        paint.setStrokeWidth(1);
        rectF.set(center-width,center-width,center+width,center+width);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawArc(rectF,270,getPercent(),true,paint);
    }


}
