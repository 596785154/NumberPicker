package com.example.administrator.numberpicker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/8/4.
 */
public class DialogNPV extends Dialog implements View.OnClickListener,
        MyNumberPicker.OnScrollListener, MyNumberPicker.OnValueChangeListener{

    private static final String TAG = "picker";

    private Context mContext;
    private Button mButtonGetInfo;
    private MyNumberPicker mNumberPickerView;
    private String[] mDisplayValues;

    public DialogNPV(Context context) {
        super(context, R.style.dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_npv);

        mNumberPickerView = (MyNumberPicker) this.findViewById(R.id.picker);
        mNumberPickerView.setOnScrollListener(this);
        mNumberPickerView.setOnValueChangedListener(this);
        mDisplayValues = mContext.getResources().getStringArray(R.array.test_display_2);
//        mNumberPickerView.refreshByNewDisplayedValues(mDisplayValues);

        mButtonGetInfo = (Button) this.findViewById(R.id.button_get_info);
        mButtonGetInfo.setOnClickListener(this);
    }

    // this method should be called after onCreate()
    public void initNPV(){
        mNumberPickerView.refreshByNewDisplayedValues(mDisplayValues);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_get_info:
                getCurrentContent();
                break;
        }
    }

    @Override
    public void onScrollStateChange(MyNumberPicker view, int scrollState) {
        Log.d(TAG, "onScrollStateChange : " + scrollState);
    }

    @Override
    public void onValueChange(MyNumberPicker picker, int oldVal, int newVal) {
        String[] content = picker.getDisplayedValues();
        if (content != null) {
            Log.d(TAG,"onValueChange content : " + content[newVal - picker.getMinValue()]);
            Toast.makeText(mContext.getApplicationContext(), "oldVal : " + oldVal + " newVal : " + newVal + "\n" +
                    mContext.getString(R.string.picked_content_is) + content[newVal - picker.getMinValue()], Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void getCurrentContent(){
        String[] content = mNumberPickerView.getDisplayedValues();
        if (content != null)
            Toast.makeText(mContext.getApplicationContext(),
                    mContext.getString(R.string.picked_content_is)
                            + content[mNumberPickerView.getValue() - mNumberPickerView.getMinValue()],
                    Toast.LENGTH_SHORT)
                    .show();
    }
}