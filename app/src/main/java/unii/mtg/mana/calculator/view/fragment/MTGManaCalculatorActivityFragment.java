package unii.mtg.mana.calculator.view.fragment;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import unii.mtg.mana.calculator.R;
import unii.mtg.mana.calculator.logic.ManaCalculator;
import unii.mtg.mana.calculator.model.ManaCalculatorInputModel;
import unii.mtg.mana.calculator.model.ManaCalculatorOutputModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class MTGManaCalculatorActivityFragment extends Fragment {
    private ManaCalculatorInputModel mManaCalculatorInputModel;


    @Bind(R.id.calculator_mana_output_white)
    TextView mWhiteLandsTextView;

    @Bind(R.id.calculator_mana_output_blue)
    TextView mBlueLandsTextView;

    @Bind(R.id.calculator_mana_output_black)
    TextView mBlackLandsTextView;

    @Bind(R.id.calculator_mana_output_red)
    TextView mRedLandsTextView;

    @Bind(R.id.calculator_mana_output_green)
    TextView mGreenLandsTextView;

    @Bind(R.id.calculator_mana_output_colorless)
    TextView mColorlessLandsTextView;


    @Bind(R.id.calculator_totalLandTextInput)
    TextInputLayout mTotalLandsTextInput;


    @Bind(R.id.calculator_whiteTextInput)
    TextInputLayout mLandsWhiteTextInput;


    @Bind(R.id.calculator_blueTextInput)
    TextInputLayout mLandsBlueTextInput;


    @Bind(R.id.calculator_blackTextInput)
    TextInputLayout mLandsBlackTextInput;


    @Bind(R.id.calculator_redTextInput)
    TextInputLayout mLandsRedTextInput;


    @Bind(R.id.calculator_greenTextInput)
    TextInputLayout mLandsGreenTextInput;


    @Bind(R.id.calculator_colorlessTextInput)
    TextInputLayout mLandsColorlessTextInput;


    public MTGManaCalculatorActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mtgmana_calculator, container, false);
        ButterKnife.bind(this, view);
        mTotalLandsTextInput.setHint(getString(R.string.mana_calculator_hint_land_total));
        mLandsWhiteTextInput.setHint(getString(R.string.mana_calculator_hint_land_white));
        mLandsBlueTextInput.setHint(getString(R.string.mana_calculator_hint_land_blue));
        mLandsBlackTextInput.setHint(getString(R.string.mana_calculator_hint_land_black));
        mLandsRedTextInput.setHint(getString(R.string.mana_calculator_hint_land_red));
        mLandsGreenTextInput.setHint(getString(R.string.mana_calculator_hint_land_green));
        mLandsColorlessTextInput.setHint(getString(R.string.mana_calculator_hint_land_colorless));
        if (mTotalLandsTextInput.getEditText() != null) {
            mTotalLandsTextInput.getEditText().addTextChangedListener(mLandTotalTextWatcher);
        }
        if (mLandsWhiteTextInput.getEditText() != null) {
            mLandsWhiteTextInput.getEditText().addTextChangedListener(mManaWhiteTextWatcher);
        }
        if (mLandsBlueTextInput.getEditText() != null) {
            mLandsBlueTextInput.getEditText().addTextChangedListener(mManaBlueTextWatcher);
        }
        if (mLandsBlackTextInput.getEditText() != null) {
            mLandsBlackTextInput.getEditText().addTextChangedListener(mManaBlackTextWatcher);
        }
        if (mLandsRedTextInput.getEditText() != null) {
            mLandsRedTextInput.getEditText().addTextChangedListener(mManaRedTextWatcher);
        }
        if (mLandsGreenTextInput.getEditText() != null) {
            mLandsGreenTextInput.getEditText().addTextChangedListener(mManaGreenTextWatcher);
        }
        if (mLandsColorlessTextInput.getEditText() != null) {
            mLandsColorlessTextInput.getEditText().addTextChangedListener(mManaColorlessTextWatcher);
        }
        mManaCalculatorInputModel = new ManaCalculatorInputModel();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mTotalLandsTextInput.getEditText() != null) {
            mTotalLandsTextInput.getEditText().removeTextChangedListener(mLandTotalTextWatcher);
        }
        if (mLandsWhiteTextInput.getEditText() != null) {
            mLandsWhiteTextInput.getEditText().removeTextChangedListener(mManaWhiteTextWatcher);
        }
        if (mLandsBlueTextInput.getEditText() != null) {
            mLandsBlueTextInput.getEditText().removeTextChangedListener(mManaBlueTextWatcher);
        }
        if (mLandsBlackTextInput.getEditText() != null) {
            mLandsBlackTextInput.getEditText().removeTextChangedListener(mManaBlackTextWatcher);
        }
        if (mLandsRedTextInput.getEditText() != null) {
            mLandsRedTextInput.getEditText().removeTextChangedListener(mManaRedTextWatcher);
        }
        if (mLandsGreenTextInput.getEditText() != null) {
            mLandsGreenTextInput.getEditText().removeTextChangedListener(mManaGreenTextWatcher);
        }
        if (mLandsColorlessTextInput.getEditText() != null) {
            mLandsColorlessTextInput.getEditText().removeTextChangedListener(mManaColorlessTextWatcher);
        }
        ButterKnife.unbind(this);
    }

    private void updateTextViews(ManaCalculatorOutputModel manaCalculatorOutputModel) {
        mWhiteLandsTextView.setText(manaCalculatorOutputModel.getManaWhite() + "");
        mBlueLandsTextView.setText(manaCalculatorOutputModel.getManaBlue() + "");
        mBlackLandsTextView.setText(manaCalculatorOutputModel.getManaBlack() + "");
        mRedLandsTextView.setText(manaCalculatorOutputModel.getManaRed() + "");
        mGreenLandsTextView.setText(manaCalculatorOutputModel.getManaGreen() + "");
        mColorlessLandsTextView.setText(manaCalculatorOutputModel.getManaColorless() + "");
    }

    private TextWatcher mLandTotalTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int landTotal = 0;
            if (s.length() > 0) {
                try {
                    landTotal = Integer.parseInt(s.toString());
                } catch (NumberFormatException e) {
                    landTotal = 0;
                }

            }
            mManaCalculatorInputModel.setLandTotal(landTotal);
            updateTextViews(ManaCalculator.calculateLandsOutput(mManaCalculatorInputModel));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher mManaWhiteTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int mana = 0;
            if (s.length() > 0) {
                try {
                    mana = Integer.parseInt(s.toString());
                } catch (NumberFormatException e) {
                    mana = 0;
                }

            }
            mManaCalculatorInputModel.setManaWhite(mana);
            mManaCalculatorInputModel.setManaTotal(ManaCalculator.calculateTotalManaCost(mManaCalculatorInputModel));
            updateTextViews(ManaCalculator.calculateLandsOutput(mManaCalculatorInputModel));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher mManaBlueTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int mana = 0;
            if (s.length() > 0) {
                try {
                    mana = Integer.parseInt(s.toString());
                } catch (NumberFormatException e) {
                    mana = 0;
                }

            }
            mManaCalculatorInputModel.setManaBlue(mana);
            mManaCalculatorInputModel.setManaTotal(ManaCalculator.calculateTotalManaCost(mManaCalculatorInputModel));
            updateTextViews(ManaCalculator.calculateLandsOutput(mManaCalculatorInputModel));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher mManaBlackTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int mana = 0;
            if (s.length() > 0) {
                try {
                    mana = Integer.parseInt(s.toString());
                } catch (NumberFormatException e) {
                    mana = 0;
                }

            }
            mManaCalculatorInputModel.setManaBlack(mana);
            mManaCalculatorInputModel.setManaTotal(ManaCalculator.calculateTotalManaCost(mManaCalculatorInputModel));
            updateTextViews(ManaCalculator.calculateLandsOutput(mManaCalculatorInputModel));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher mManaRedTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int mana = 0;
            if (s.length() > 0) {
                try {
                    mana = Integer.parseInt(s.toString());
                } catch (NumberFormatException e) {
                    mana = 0;
                }

            }
            mManaCalculatorInputModel.setManaRed(mana);
            mManaCalculatorInputModel.setManaTotal(ManaCalculator.calculateTotalManaCost(mManaCalculatorInputModel));
            updateTextViews(ManaCalculator.calculateLandsOutput(mManaCalculatorInputModel));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher mManaGreenTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int mana = 0;
            if (s.length() > 0) {
                try {
                    mana = Integer.parseInt(s.toString());
                } catch (NumberFormatException e) {
                    mana = 0;
                }

            }
            mManaCalculatorInputModel.setManaGreen(mana);
            mManaCalculatorInputModel.setManaTotal(ManaCalculator.calculateTotalManaCost(mManaCalculatorInputModel));
            updateTextViews(ManaCalculator.calculateLandsOutput(mManaCalculatorInputModel));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher mManaColorlessTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int mana = 0;
            if (s.length() > 0) {
                try {
                    mana = Integer.parseInt(s.toString());
                } catch (NumberFormatException e) {
                    mana = 0;
                }

            }
            mManaCalculatorInputModel.setManaColorless(mana);
            mManaCalculatorInputModel.setManaTotal(ManaCalculator.calculateTotalManaCost(mManaCalculatorInputModel));
            updateTextViews(ManaCalculator.calculateLandsOutput(mManaCalculatorInputModel));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
