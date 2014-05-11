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

	final int size = 35;
	final int width = 5;
	final float cornerRadii = 3.5f;
	
	private boolean isChecked = false;
	
	private final int insidePadding = 9;
	
	
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
		if(!isInEditMode())
		{
			TypedArray tA = context.obtainStyledAttributes(attrs,
					R.styleable.wingokuflatui);

			final String userColor = tA
					.getString(R.styleable.wingokuflatui_cbColor);
			

			tA.recycle();
			
			if(userColor == null || userColor.isEmpty())
			{
				throw new NullPointerException("Checkbox color is not provided!");
			}
		
			
			setDrawables(userColor);
		}
	}
	
	private void setDrawables(String userColor)
	{
		final GradientDrawable frame = new GradientDrawable();
		
		frame.setStroke(width, Color.parseColor(userColor));
		
		
		// setting cornerRadii
		frame.setCornerRadius(cornerRadii);
		
		final PaintDrawable pDrawable = new PaintDrawable(Color.parseColor(userColor));
			
		
		pDrawable.setCornerRadius(cornerRadii);
		pDrawable.setIntrinsicHeight(size);
		pDrawable.setIntrinsicWidth(size);
		
		
		
		
		InsetDrawable inDrawable = new InsetDrawable(pDrawable, insidePadding, insidePadding, insidePadding, insidePadding);
		
		 Drawable[] checked = {frame, inDrawable};
	     LayerDrawable checkedLayers = new LayerDrawable(checked);

		
		final PaintDrawable pDrawable2 = new PaintDrawable(Color.TRANSPARENT);
			
		
		pDrawable2.setCornerRadius(cornerRadii);
		pDrawable2.setIntrinsicHeight(size);
		pDrawable2.setIntrinsicWidth(size);
		
		
		InsetDrawable inDrawable2 = new InsetDrawable(pDrawable2, insidePadding, insidePadding, insidePadding, insidePadding);
		
		Drawable[] unchecked = {frame, pDrawable2};
	     LayerDrawable uncheckedLayers = new LayerDrawable(unchecked);
		
		// disabled
		final GradientDrawable frame2 = new GradientDrawable();
		
		String tempColor = "#aa";
		
		if(userColor.length() == 4)
		{
			for(int i=0; i<3; i++)
				tempColor+=userColor.charAt(1); // # is at 0th index
		}
		else
			if(userColor.length() == 7) // length is 7 because # is also in the string 
			{
				String temp = userColor;
				
				tempColor+=temp.replace("#", "");
				
				System.out.println(tempColor+" "+temp.replace("#", "") + " "+ temp.substring(2, userColor.length()));
			}
			else
				if(userColor.length() == 9)
				{
					String temp = userColor;
					
					tempColor+= temp.substring(2, userColor.length());
				}
		
		frame2.setStroke(width, Color.parseColor(tempColor));
		
		
		// setting cornerRadii
		frame2.setCornerRadius(cornerRadii);
		
		final PaintDrawable pDrawable3 = new PaintDrawable(Color.parseColor(tempColor));
			
		
		pDrawable3.setCornerRadius(cornerRadii);
		pDrawable3.setIntrinsicHeight(size);
		pDrawable3.setIntrinsicWidth(size);
		
		InsetDrawable inDrawable3 = new InsetDrawable(pDrawable3, insidePadding, insidePadding, insidePadding, insidePadding);
		
		 Drawable[] d_checked = {frame2, inDrawable3};
	     LayerDrawable d_checkedLayers = new LayerDrawable(d_checked);

		
		final PaintDrawable pDrawable4 = new PaintDrawable(Color.TRANSPARENT);
			
		
		pDrawable4.setCornerRadius(cornerRadii);
		pDrawable4.setIntrinsicHeight(size);
		pDrawable4.setIntrinsicWidth(size);
		
		
		InsetDrawable inDrawable4 = new InsetDrawable(pDrawable4, insidePadding, insidePadding, insidePadding, insidePadding);
		
		 Drawable[] d_unchecked = {frame2, inDrawable4};
	     LayerDrawable d_uncheckedLayers = new LayerDrawable(d_unchecked);
	     
	     
	     StateListDrawable states = new StateListDrawable();
	        states.addState(new int[]{-android.R.attr.state_checked, android.R.attr.state_enabled}, uncheckedLayers);
	        states.addState(new int[]{android.R.attr.state_checked, android.R.attr.state_enabled}, checkedLayers);
	        
	        states.addState(new int[]{-android.R.attr.state_checked, -android.R.attr.state_enabled}, d_uncheckedLayers);
	        states.addState(new int[]{android.R.attr.state_checked, -android.R.attr.state_enabled}, d_checkedLayers);

	        setButtonDrawable(states);


	}
}
