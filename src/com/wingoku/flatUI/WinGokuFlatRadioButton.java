package com.wingoku.flatUI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.PaintDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;

public class WinGokuFlatRadioButton extends RadioButton{

	final int size = 40;
	final int width = 5;
	final float cornerRadii = 10f;
	
	private boolean isChecked = false;
	
	

	
	public WinGokuFlatRadioButton(Context context) {
		super(context);

		initializeShit(context,null);
	}

	

	
	public WinGokuFlatRadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		initializeShit(context,attrs);
		
	}
	
	public WinGokuFlatRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		
		initializeShit(context, attrs);
		
		
	}
	
	private void initializeShit(Context context, AttributeSet attrs)
	{
		if(!isInEditMode())
		{
			setText(""); // removing text set by the user inside the radioButton widget, because it appears inside the radioButton for some reason
		
		//isRadioButtonEnabled();
			
			this.setBackground(getResources().getDrawable(R.drawable.radio_background));
			
			TypedArray tA = context.obtainStyledAttributes(attrs,
					R.styleable.wingokuflatui);
	
			final String userColor = tA
					.getString(R.styleable.wingokuflatui_rbColor);
			
	
			tA.recycle();
			
			if(userColor == null || userColor.isEmpty())
			{
				throw new NullPointerException("RadioButton color is not provided!");
			}
			
			GradientDrawable gDrawable = (GradientDrawable) this.getBackground();
			
			gDrawable.setStroke(width, Color.parseColor(userColor));
			
			
			// setting radius
			//gDrawable.setCornerRadius(cornerRadii);
			
			this.setBackground(gDrawable);
			
			
			setInsides(Color.TRANSPARENT);
			
			
			this.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View arg0, MotionEvent event) {
					
					isRadioButtonEnabled();
					
					if(event.getAction() == MotionEvent.ACTION_DOWN)
					{
						if(!isChecked)
						{
							isChecked = true;
							setInsides(Color.parseColor(userColor));
							
							WinGokuFlatRadioButton.this.invalidate();
							//WinGokuFlatCheckBox.this.setButtonDrawable(inDrawable);
							
						}
						else
						{
							isChecked = false;
							setInsides(Color.TRANSPARENT);
		
							WinGokuFlatRadioButton.this.invalidate();
						}
					
					}				
					return false;
				}
			});	
		}
	}
	
	private void isRadioButtonEnabled() {
		
		if(isEnabled())
		{
			WinGokuFlatRadioButton.this.getBackground().setAlpha(255);
			WinGokuFlatRadioButton.this.setEnabled(true);
		}
		else
		{
			WinGokuFlatRadioButton.this.getBackground().setAlpha(128);
			WinGokuFlatRadioButton.this.setEnabled(false);
		}
	}

	
	private void setInsides(int color)
	{
		final PaintDrawable pDrawable = new PaintDrawable(color);
			
		
		pDrawable.setCornerRadius(cornerRadii);
		pDrawable.setIntrinsicHeight(size);
		pDrawable.setIntrinsicWidth(size);
		
		
		InsetDrawable inDrawable = new InsetDrawable(pDrawable, 10, 10, 10, 10);
		
		this.setButtonDrawable(inDrawable);
	}
}
