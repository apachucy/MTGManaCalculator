package unii.mtg.mana.calculator.view.model.mana;


import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import unii.mtg.mana.calculator.R;
import unii.mtg.mana.calculator.view.model.DataUpdater;
import unii.mtg.mana.calculator.view.listener.ManaSeekBarListener;

public class InputManaViewModel implements IManaViewModel {
    private TextView mManaSymbolTextView;
    private DiscreteSeekBar mSeekBar;
    private ImageView mImageView;

    public InputManaViewModel(int minValue, int maxValue, Drawable icon, View view, DataUpdater dataUpdater) {
        mManaSymbolTextView = (TextView) view.findViewById(R.id.calculator_mana_value);
        mSeekBar = (DiscreteSeekBar) view.findViewById(R.id.calculator_mana_slider);
        mImageView = (ImageView) view.findViewById(R.id.calculator_mana_ic);
        mManaSymbolTextView.setText(Integer.toString(minValue));
        mImageView.setImageDrawable(icon);
        mSeekBar.setMin(minValue);
        mSeekBar.setMax(maxValue);
        mSeekBar.setOnProgressChangeListener(new ManaSeekBarListener(mManaSymbolTextView, dataUpdater));
    }

    @Override
    public void onDestroy() {
        mSeekBar.setOnProgressChangeListener(null);
    }

    @Override
    public void setMaxValue(int newMaxValue) {
        mSeekBar.setMax(newMaxValue);
        mSeekBar.setProgress(mSeekBar.getMin());
    }

    @Override
    public int getCurrentValue() {
        return mSeekBar.getProgress();
    }


}
