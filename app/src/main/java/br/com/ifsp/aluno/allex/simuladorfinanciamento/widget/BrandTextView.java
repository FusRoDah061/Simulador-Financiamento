package br.com.ifsp.aluno.allex.simuladorfinanciamento.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import br.com.ifsp.aluno.allex.simuladorfinanciamento.R;

public class BrandTextView extends TextView {
    public BrandTextView(Context context) {
        super(context);
        setFont(context);
    }

    public BrandTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public BrandTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    public BrandTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setFont(context);
    }

    private void setFont(Context context) {
        Typeface font = ResourcesCompat.getFont(context, R.font.simple_tfb);
        setTypeface(font, Typeface.NORMAL);
    }
}
