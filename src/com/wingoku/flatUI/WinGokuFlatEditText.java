package com.wingoku.flatUI;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;

public class WinGokuFlatEditText extends EditText{

	String backgroundColor;
	
	
	public WinGokuFlatEditText(Context context) {
		super(context);

		initialize(context,null);
	}

	

	
	public WinGokuFlatEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		initialize(context, attrs);
	}
	
	public WinGokuFlatEditText(Context context, AttributeSet attrs) {
		super(context, attrs);

		initialize(context, attrs);

	}

	private void initialize(Context context, AttributeSet attrs)
	{
		if(!isInEditMode())
		{
			this.setBackground(getResources().getDrawable(R.drawable.background));
		
		
			TypedArray tA = context.obtainStyledAttributes(attrs,
					R.styleable.wingokuflatui);
	
			backgroundColor = tA
					.getString(R.styleable.wingokuflatui_backgroundColor);
	
	
			tA.recycle();
			
			this.getBackground().setColorFilter(Color.parseColor(backgroundColor),
					PorterDuff.Mode.DARKEN);

		}

	}
}
