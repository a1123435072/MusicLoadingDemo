package com.itheima.musicloadingdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sszz on 2017/1/17.
 */

public class Music extends View {

	private Paint paint;
	private int length;

	public Music(Context context) {
		this(context, null);
	}

	public Music(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Music(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		length = Math.min(w, h);
	}

	private float smallRadius = 5f;
	private float startAngle1 = 0;
	private float startAngle2 = 180;
	private float sweepAngle = 80;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		startAngle1+=5;
		startAngle2+=10;
		//1,绘制两个圆
		paint.setStrokeWidth(5);
		canvas.drawCircle(length / 2, length / 2, smallRadius, paint);
		paint.setStrokeWidth(2);
		canvas.drawCircle(length / 2, length / 2, length / 2 - smallRadius, paint);

		//2,绘制四小段弧
		RectF rectF = new RectF(length / 2 - length / 3, length / 2 - length / 3, length / 2 + length / 3, length / 2 + length / 3);
		canvas.drawArc(rectF, startAngle1, sweepAngle, false, paint);
		canvas.drawArc(rectF, startAngle2, sweepAngle, false, paint);

		rectF = new RectF(length / 2 - length / 4, length / 2 - length / 4, length / 2 + length / 4, length / 2 + length / 4);
		canvas.drawArc(rectF,startAngle1,sweepAngle,false,paint);
		canvas.drawArc(rectF,startAngle2,sweepAngle,false,paint);
		if(isStart) {
			invalidate();
		}
	}
	private boolean isStart=true;

	//当自定义控件脱离窗体,即将销毁的时候
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		isStart=false;
	}
}
