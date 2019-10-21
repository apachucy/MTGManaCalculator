package unii.mtg.mana.calculator.view.model.mana;


import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.view.View;

import unii.mtg.mana.calculator.R;
import unii.mtg.mana.calculator.view.model.DataUpdater;

public class ManaInputHolder {
    private InputManaViewModel mBlue;
    private InputManaViewModel mRed;
    private InputManaViewModel mWhite;
    private InputManaViewModel mGreen;
    private InputManaViewModel mBlack;
    private InputManaViewModel mColorLess;
    private Context mContext;

    public ManaInputHolder(Context context, View view, int minValue, int maxValue, DataUpdater dataUpdater) {
        mContext = context;
        mBlue = new InputManaViewModel(minValue, maxValue, getDrawable(R.mipmap.spinner_blue), view.findViewById(R.id.mana_input_blue), dataUpdater);
        mBlack = new InputManaViewModel(minValue, maxValue, getDrawable(R.mipmap.spinner_black), view.findViewById(R.id.mana_input_black), dataUpdater);
        mRed = new InputManaViewModel(minValue, maxValue, getDrawable(R.mipmap.spinner_red), view.findViewById(R.id.mana_input_red), dataUpdater);
        mGreen = new InputManaViewModel(minValue, maxValue, getDrawable(R.mipmap.spinner_green), view.findViewById(R.id.mana_input_green), dataUpdater);
        mWhite = new InputManaViewModel(minValue, maxValue, getDrawable(R.mipmap.spinner_white), view.findViewById(R.id.mana_input_white), dataUpdater);
        mColorLess = new InputManaViewModel(minValue, maxValue, getDrawable(R.mipmap.spinner_colorless), view.findViewById(R.id.mana_input_colorless), dataUpdater);
    }

    private Drawable getDrawable(int resName) {
        return ContextCompat.getDrawable(mContext, resName);
    }

    public void onDestroy() {
        mBlue.onDestroy();
        mBlack.onDestroy();
        mRed.onDestroy();
        mGreen.onDestroy();
        mWhite.onDestroy();
        mColorLess.onDestroy();
    }

    public InputManaViewModel getBlue() {
        return mBlue;
    }

    public InputManaViewModel getRed() {
        return mRed;
    }


    public InputManaViewModel getWhite() {
        return mWhite;
    }

    public InputManaViewModel getGreen() {
        return mGreen;
    }

    public InputManaViewModel getBlack() {
        return mBlack;
    }

    public InputManaViewModel getColorLess() {
        return mColorLess;
    }

    public void setMaxBarValue(int maxValue) {
        mBlue.setMaxValue(maxValue);
        mRed.setMaxValue(maxValue);
        mGreen.setMaxValue(maxValue);
        mWhite.setMaxValue(maxValue);
        mBlack.setMaxValue(maxValue);
        mColorLess.setMaxValue(maxValue);
    }
}
