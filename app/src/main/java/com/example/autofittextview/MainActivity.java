package com.example.autofittextview;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.grantland.widget.AutofitHelper;
import me.grantland.widget.AutofitLayout;
import me.grantland.widget.AutofitTextView;

/**
 * Demo autofittextview 用法
 * <p/>
 * Button textView  使用AutofitLayout 綁定TextView
 * TextView textView2   使用AutofitHelper 綁定TextView
 * AutofitTextView textView3    AutofitTextView
 * TextView textView4  一般 TextView
 */
public class MainActivity extends AppCompatActivity implements AutofitHelper.OnTextSizeChangeListener {
    AutofitLayout layout;
    Button textView;
    TextView textView2;
    AutofitTextView textView3;
    TextView textView4;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        setContentView(linearLayout);
    }

    /**
     * 設定 每個TextView text
     *
     * @return
     */
    private TextWatcher editChangeListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                autofitTextView.setText(s);
                textView.setText(s);
                textView2.setText(s);
                textView3.setText(s);
                textView4.setText(s);

                Log.e("textsize", String.valueOf(textView.getTextSize()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void init() {
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );


        EditText editText = new EditText(this);
        editText.addTextChangedListener(editChangeListener());
        editText.setLayoutParams(editParams);
        layout = new AutofitLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layout.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams textParams = new FrameLayout.LayoutParams(
                500,
                400
        );

        textView = new Button(this);
        textView.setBackgroundColor(Color.RED);
        textView.setLayoutParams(textParams);
        textView.setMaxLines(3);

        //把textview  加入 AutofitLayout
        layout.addView(textView);

        //取出 AutofitHelper 設定
        AutofitHelper layoutHelper = layout.getAutofitHelper(textView);
        layoutHelper.setEnabled(true);
        layoutHelper.setMinTextSize(20);
        layoutHelper.setMaxTextSize(50);
        layoutHelper.addOnTextSizeChangeListener(this);
        //必須要限制行數
//        layoutHelper.setMaxLines(1);
//=========================================
        textView2 = new TextView(this);
        textView2.setBackgroundColor(Color.YELLOW);
        textView2.setLayoutParams(textParams);
        textView2.setMaxLines(3);


        AutofitHelper textViewHelper2 = AutofitHelper.create(textView2);
        textViewHelper2.setEnabled(true);
        textViewHelper2.setMinTextSize(20);
        textViewHelper2.setMaxTextSize(50);
//        textViewHelper2.setMaxLines(3);

        textView3 = new AutofitTextView(this);
        textView3.setBackgroundColor(Color.BLUE);
        textView3.setMinTextSize(20);
        textView3.setMaxTextSize(50);
        textView3.setLayoutParams(textParams);
        textView3.setMaxLines(3);


        textView4 = new TextView(this);
        textView4.setBackgroundColor(Color.GREEN);
        textView4.setLayoutParams(textParams);
        textView4.setTextSize(30);
        textView4.setMaxLines(3);


        linearLayout.addView(editText);
        linearLayout.addView(layout);
        linearLayout.addView(textView2);
        linearLayout.addView(textView3);
        linearLayout.addView(textView4);
    }

    @Override
    public void onTextSizeChange(float textSize, float oldTextSize) {
        Log.e("size", "size cheange");
    }

}
