package unii.mtg.mana.calculator.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import unii.mtg.mana.calculator.R;
import unii.mtg.mana.calculator.logic.ManaCalculator;
import unii.mtg.mana.calculator.model.ManaCalculatorInputModel;
import unii.mtg.mana.calculator.model.ManaCalculatorOutputModel;
import unii.mtg.mana.calculator.util.diagram.DiagramCreatorUtil;
import unii.mtg.mana.calculator.view.model.DataUpdater;
import unii.mtg.mana.calculator.view.model.mana.ManaInputHolder;

/**
 * A placeholder fragment containing a simple view.
 */
public class MTGManaCalculatorActivityFragment extends BaseFragment {

    private final static String TAG = MTGManaCalculatorActivityFragment.class.toString();
    private ManaCalculatorInputModel mManaCalculatorInputModel;
    private static final int BAR_MIN_VALUE = 0;
    private static final int BAR_MAX_VALUE = 30;
    private int mMaxValue;
    private ManaInputHolder mManaInputHolder;

    @Bind(R.id.calculator_totalLandTextInput)
    TextInputLayout mTotalLandsTextInput;

    @Bind(R.id.calculator_mana_output)
    BarGraph mBarGraph;

    public MTGManaCalculatorActivityFragment() {
        mMaxValue = BAR_MAX_VALUE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mtgmana_calculator, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        initData();
        initView(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mTotalLandsTextInput.getEditText() != null) {
            mTotalLandsTextInput.getEditText().removeTextChangedListener(mLandTotalTextWatcher);
        }
        mManaInputHolder.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                showInfoDialog(getActivity(), getString(R.string.dialog_info_title),
                        getString(R.string.dialog_info_body), getString(R.string.dialog_positive_action));
                return true;
            case R.id.action_settings:
                showInputDialog(getActivity(), getString(R.string.dialog_spinner_max_value_title),
                        getString(R.string.dialog_spinner_max_value_body), getString(R.string.dialog_spinner_hint),
                        Integer.toString(mMaxValue), mInputCallback);
                return true;
            default:
                break;
        }

        return false;
    }

    private void initView(View view) {
        mTotalLandsTextInput.setHint(getString(R.string.mana_calculator_hint_land_total));

        if (mTotalLandsTextInput.getEditText() != null) {
            mTotalLandsTextInput.getEditText().addTextChangedListener(mLandTotalTextWatcher);
        }
        mManaInputHolder = new ManaInputHolder(getActivity(), view, BAR_MIN_VALUE, BAR_MAX_VALUE, mDataUpdater);

    }

    private void initData() {
        mManaCalculatorInputModel = new ManaCalculatorInputModel();

    }

    private DataUpdater mDataUpdater = new DataUpdater() {
        @Override
        public void onDataChanged() {
            mManaCalculatorInputModel.setManaBlack(mManaInputHolder.getBlack().getCurrentValue());
            mManaCalculatorInputModel.setManaBlue(mManaInputHolder.getBlue().getCurrentValue());
            mManaCalculatorInputModel.setManaGreen(mManaInputHolder.getGreen().getCurrentValue());
            mManaCalculatorInputModel.setManaRed(mManaInputHolder.getRed().getCurrentValue());
            mManaCalculatorInputModel.setManaWhite(mManaInputHolder.getWhite().getCurrentValue());
            mManaCalculatorInputModel.setManaColorless(mManaInputHolder.getColorLess().getCurrentValue());
            mManaCalculatorInputModel.calculateTotalMana();
            createDiagram(ManaCalculator.calculateLandsOutput(mManaCalculatorInputModel));
        }
    };

    private void createDiagram(ManaCalculatorOutputModel manaCalculatorOutputModel) {
        ArrayList<Bar> barArrayList = DiagramCreatorUtil.createDataForDiagram(getActivity(), manaCalculatorOutputModel, mManaCalculatorInputModel);
        mBarGraph.setBars(barArrayList);
        mBarGraph.setUnit(" ");
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
            mManaCalculatorInputModel.calculateTotalMana();
            createDiagram(ManaCalculator.calculateLandsOutput(mManaCalculatorInputModel));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private MaterialDialog.InputCallback mInputCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            try {
                int newMaxValue = Integer.parseInt(input.toString());
                if (newMaxValue <= BAR_MIN_VALUE) {
                    return;
                }
                mMaxValue = newMaxValue;
                mManaInputHolder.setMaxBarValue(mMaxValue);
            } catch (NumberFormatException e) {
                Log.e(TAG, e.toString());
            }
        }
    };

}
