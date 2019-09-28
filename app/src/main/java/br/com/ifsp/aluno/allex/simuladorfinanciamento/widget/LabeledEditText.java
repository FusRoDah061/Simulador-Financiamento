package br.com.ifsp.aluno.allex.simuladorfinanciamento.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.R;

public class LabeledEditText extends LinearLayout {

    @StyleableRes
    int indexLabel = 0;

    TextView innerLabel;
    EditText innerEditText;

    public LabeledEditText(Context context) {
        super(context);
        init(context, null);
    }

    public LabeledEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LabeledEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public LabeledEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        inflate(context, R.layout.labeled_number_edit_text, this);

        initComponents();

        if(attrs != null) {
            int[] sets = { R.attr.label };
            TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
            CharSequence label = typedArray.getText(indexLabel);
            typedArray.recycle();

            innerLabel.setText(label);
        }
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        innerEditText.setOnFocusChangeListener(l);
    }

    private void initComponents() {
        innerLabel = (TextView) findViewById(R.id.lnetTextView);
        innerEditText = (EditText) findViewById(R.id.lnetEditText);
    }

    public Double getValue() {
        try {
            return Double.parseDouble(innerEditText.getText().toString());
        }
        catch (Exception e) {
            return 0.0;
        }
    }

    public void setValue(double entrada) {
        innerEditText.setText(String.valueOf(entrada));
    }

    public int getInnerEditTextId() {
        return innerEditText.getId();
    }
}
