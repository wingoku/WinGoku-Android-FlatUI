package com.wingoku.flatUI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;

public class WinGokuFlatCheckBox extends CheckBox{

	final int size = 30;
	final int width = 5;
	final float cornerRadii = 3.5f;
	
	private boolean isChecked = false;
	
	
	public WinGokuFlatCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		
		initializeShit(context, attrs);

	
	}
	
	
	public WinGokuFlatCheckBox(Context context) {
		super(context);

		initializeShit(context,null);
	}

	

	
	public WinGokuFlatCheckBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		initializeShit(context,attrs);
		
	}
	
	
	private void initializeShit(Context context, AttributeSet attrs)
	{
		setText("");
		//isCheckBoxEnabled();
		
		this.setBackground(getResources().getDrawable(R.drawable.checkbox_background));
		
		TypedArray tA = context.obtainStyledAttributes(attrs,
				R.styleable.wingokuflatui);

		final String userColor = tA
				.getString(R.styleable.wingokuflatui_cbColor);
		

		tA.recycle();
		
		if(userColor == null || userColor.isEmpty())
		{
			throw new NullPointerException("Checkbox color is not provided!");
		}
		
		final GradientDrawable gDrawable = (GradientDrawable) this.getBackground();
		
		gDrawable.setStroke(width, Color.parseColor(userColor));
		
		
		// setting cornerRadii
		gDrawable.setCornerRadius(cornerRadii);
		
		this.setBackground(gDrawable);

		
		setInsides(Color.TRANSPARENT);
		
		
		this.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				
				
				isCheckBoxEnabled();
				
				
				if(event.getAction() == MotionEvent.ACTION_DOWN)
				{
					if(!isChecked)
					{
						isChecked = true;
						setInsides(Color.parseColor(userColor));
						
						WinGokuFlatCheckBox.this.invalidate();
						//WinGokuFlatCheckBox.this.setButtonDrawable(inDrawable);
						
					}
					else
					{
						isChecked = false;
	
						setInsides(Color.TRANSPARENT);
	
						WinGokuFlatCheckBox.this.invalidate();
					
					}
				
				}				
				return false;
			}

			
		});
		
	}
	
	private void isCheckBoxEnabled() {
		
		if(isEnabled())
		{
			WinGokuFlatCheckBox.this.setAlpha(1f);
			WinGokuFlatCheckBox.this.setEnabled(true);
		}
		else
		{
			WinGokuFlatCheckBox.this.setAlpha(0.7f);
			WinGokuFlatCheckBox.this.setEnabled(false);
		}
	}


	
	private void setInsides(int color)
	{
		final PaintDrawable pDrawable = new PaintDrawable(color);
			
		
		pDrawable.setCornerRadius(cornerRadii);
		pDrawable.setIntrinsicHeight(size);
		pDrawable.setIntrinsicWidth(size);
		
		
		InsetDrawable inDrawable = new InsetDrawable(pDrawable, 5, 5, 5, 5);
		
		this.setButtonDrawable(inDrawable);
	}
}
