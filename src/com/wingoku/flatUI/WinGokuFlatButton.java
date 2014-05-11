package com.wingoku.flatUI;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class WinGokuFlatButton extends Button {

	private String normalStatecolor, pressedStateColor;

	public WinGokuFlatButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		testing(context,attrs);
		
	}

	
	public WinGokuFlatButton(Context context) {
		super(context);

		testing(context,null);
	}

	

	
	public WinGokuFlatButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		testing(context,attrs);
		//this.setBackground(getResources().getDrawable(R.drawable.background));
	}
	
	private void testing(Context context, AttributeSet attrs)
	{
		this.setBackground(getResources().getDrawable(R.drawable.background));

		if(attrs!=null)
		{
			TypedArray tA = context.obtainStyledAttributes(attrs,
					R.styleable.wingokuflatui);
	
			normalStatecolor = tA
					.getString(R.styleable.wingokuflatui_normalStateColor);
	
			pressedStateColor = tA
					.getString(R.styleable.wingokuflatui_pressedStateColor);
			
			tA.recycle();
		}
		else
		{
			normalStatecolor = pressedStateColor = "#426689";
		}
		this.getBackground().setColorFilter(Color.parseColor(normalStatecolor),
				PorterDuff.Mode.DARKEN);

		this.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent event) {

				final String color;
				
				if (event.getAction() == MotionEvent.ACTION_UP) {
					color = normalStatecolor;

				} else
					color = pressedStateColor;

			 	new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {

						// color change when the button is pressed

						WinGokuFlatButton.this.getBackground()
								.setColorFilter(Color.parseColor(color),
										PorterDuff.Mode.DARKEN);
					}
				}, 30);

				return false;
			}
		});
	}

}
