package com.garrettshorr.marscompanion.Widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.garrettshorr.marscompanion.R;

/**
 * Created by g on 2/18/2017.
 */
public class HorizontalNumberPicker extends LinearLayout {

    private Button buttonMinus, buttonPlus;
    private TextView textValue;
    private int minValue;
    private int maxValue;
    private int currentValue;
    private int mode;
    public static final String TAG = "HORIZONTAL NUMBER PICKER";

    public HorizontalNumberPicker(Context context) {
        super(context);
        init(context);
    }

    public HorizontalNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttributes( context,  attrs);
        init(context);
    }

    public HorizontalNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttributes( context,  attrs);
        init(context);
    }

    public HorizontalNumberPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        readAttributes( context,  attrs);
        init(context);
    }

    public void readAttributes(Context context, AttributeSet attrs)
    {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HorizontalNumberPicker);
        this.minValue = a.getInteger(R.styleable.HorizontalNumberPicker_minValue, 0);
        this.currentValue = a.getInteger(R.styleable.HorizontalNumberPicker_currentValue, 0);
        this.mode = a.getInteger(R.styleable.HorizontalNumberPicker_mode,0);
        this.maxValue = a.getInteger(R.styleable.HorizontalNumberPicker_maxValue,Integer.MAX_VALUE);
        a.recycle();
    }

    private void init(Context context) {
        this.setOrientation(LinearLayout.HORIZONTAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.horizontal_num_picker, null);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        buttonMinus = (Button) layout.findViewById(R.id.button_minus);
        buttonPlus = (Button) layout.findViewById(R.id.button_plus);
        textValue = (TextView) layout.findViewById(R.id.text_value);
        textValue.setText(currentValue+"");
        adjustForMode(layout);
        setButtonStatus();
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrement();
            }
        });
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment();
            }
        });
        this.addView(layout);
    }

    private void setButtonStatus() {
        if(currentValue <= minValue)
            buttonMinus.setEnabled(false);
        if(currentValue >= maxValue)
            buttonPlus.setEnabled(false);
    }

    private void adjustForMode(LinearLayout layout) {
        switch(mode) {
            case 1: //current value of resources
                textValue.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 2: //temperature
                textValue.setTextColor(Color.parseColor("#FFFFFF"));
                layout.setBackgroundColor(Color.parseColor("#FF0000"));
                break;
            case 3: //oxygen
                textValue.setTextColor(Color.parseColor("#000080"));
                layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case 4: //oceans
                textValue.setTextColor(Color.parseColor("#FFFFFF"));
                layout.setBackgroundColor(Color.parseColor("#000080"));
                break;
            case 5: //terraforming rating
                textValue.setTextColor(Color.parseColor("#FFFFFF"));
                layout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec,heightMeasureSpec);
        //int height = MeasureSpec.getSize(heightMeasureSpec);
        //setMeasuredDimension(height, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


    private void increment() {
        if(currentValue < maxValue) {
            currentValue++;
            if(mode == 2)   //temp goes by 2s
                currentValue++;
            textValue.setText(currentValue + "");
        }
        if(currentValue == maxValue)
            buttonPlus.setEnabled(false);
        if(!buttonMinus.isEnabled())
            buttonMinus.setEnabled(true);
    }

    private void decrement() {
        if(currentValue > minValue) {
            currentValue--;
            if(mode == 2)   //temp goes by 2s
                currentValue--;
            textValue.setText(currentValue+"");
        }
        if(currentValue == minValue)
            buttonMinus.setEnabled(false);
        if(!buttonPlus.isEnabled())
            buttonPlus.setEnabled(true);
    }
}
