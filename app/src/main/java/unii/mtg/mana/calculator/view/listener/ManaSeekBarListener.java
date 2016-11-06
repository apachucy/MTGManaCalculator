package unii.mtg.mana.calculator.view.listener;


import android.widget.TextView;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import unii.mtg.mana.calculator.view.model.DataUpdater;

public class ManaSeekBarListener implements DiscreteSeekBar.OnProgressChangeListener {
    private TextView mTextView;
    private DataUpdater mDataUpdater;

    public ManaSeekBarListener(TextView textViewToBeUpdated,DataUpdater dataUpdater) {
        mTextView = textViewToBeUpdated;
        mDataUpdater = dataUpdater;
    }

    @Override
    public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
        mTextView.setText(Integer.toString(seekBar.getProgress()));
        if (mDataUpdater != null) {
            mDataUpdater.onDataChanged();
        }
    }

    @Override
    public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
    }


}
