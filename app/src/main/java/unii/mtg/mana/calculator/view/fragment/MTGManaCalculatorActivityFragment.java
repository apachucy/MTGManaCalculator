package unii.mtg.mana.calculator.view.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;
import unii.mtg.mana.calculator.R;
import unii.mtg.mana.calculator.logic.GuildTypeCalculator;
import unii.mtg.mana.calculator.logic.ManaCalculator;
import unii.mtg.mana.calculator.model.Guild;
import unii.mtg.mana.calculator.model.ManaCalculatorInputModel;
import unii.mtg.mana.calculator.model.ManaCalculatorOutputModel;
import unii.mtg.mana.calculator.persitance.BaseRunSharedPreferences;
import unii.mtg.mana.calculator.persitance.IBaseRunPreferences;
import unii.mtg.mana.calculator.util.diagram.DiagramCreatorUtil;
import unii.mtg.mana.calculator.view.model.DataUpdater;
import unii.mtg.mana.calculator.view.model.mana.ManaInputHolder;

/**
 * A placeholder fragment containing a simple view.
 */
public class MTGManaCalculatorActivityFragment extends BaseFragment {

    private static final String TAG = MTGManaCalculatorActivityFragment.class.toString();
    private static final int BAR_MIN_VALUE = 0;
    private static final int BAR_MAX_VALUE = 30;

    private ChainTourGuide mTutorialHandler = null;
    private ManaCalculatorInputModel mManaCalculatorInputModel;
    private MaterialDialog mMaterialDialog;
    private int mMaxValue;
    private ManaInputHolder mManaInputHolder;
    private boolean mDisplayGuide;
    private IBaseRunPreferences mGamePreferences;
    private Unbinder mUnbinder;

    @BindView(R.id.calculator_totalLandTextInput)
    TextInputLayout mTotalLandsTextInput;
    @BindView(R.id.calculator_mana_output)
    BarGraph mBarGraph;
    @BindView(R.id.ic_guild)
    ImageView mGuildImage;
    @BindView(R.id.guild_view_description)
    View mGuildDescription;
    @BindView(R.id.guild_description)
    TextView mGuildText;

    public MTGManaCalculatorActivityFragment() {
        mMaxValue = BAR_MAX_VALUE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mtgmana_calculator, container, false);
        mUnbinder = ButterKnife.bind(this, view);
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
        mUnbinder.unbind();
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
                mMaterialDialog = showInfoDialog(getActivity(), getString(R.string.dialog_info_title),
                        getString(R.string.dialog_info_body), getString(R.string.dialog_positive_action));
                return true;
            case R.id.action_settings:
                mMaterialDialog = showInputDialog(getActivity(), getString(R.string.dialog_spinner_max_value_title),
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
        if (mDisplayGuide) {
            initGuide(view);

        }
    }

    private void initData() {
        mManaCalculatorInputModel = new ManaCalculatorInputModel();
        mGamePreferences = new BaseRunSharedPreferences(getActivity());
        mDisplayGuide = mGamePreferences.isFirstRun();
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
            displayResults(ManaCalculator.calculateLandsOutput(mManaCalculatorInputModel));
        }
    };

    private void displayResults(ManaCalculatorOutputModel manaCalculatorOutputModel) {
        createGuildDescription(manaCalculatorOutputModel);
        createDiagram(manaCalculatorOutputModel);
    }

    private void createDiagram(ManaCalculatorOutputModel manaCalculatorOutputModel) {
        ArrayList<Bar> barArrayList = DiagramCreatorUtil.createDataForDiagram(getActivity(), manaCalculatorOutputModel, mManaCalculatorInputModel);
        mBarGraph.setBars(barArrayList);
        mBarGraph.setUnit(" ");
    }

    private void createGuildDescription(ManaCalculatorOutputModel manaCalculatorOutputModel) {
        Guild resource = GuildTypeCalculator.calculateImageResource(manaCalculatorOutputModel);
        if (resource.getImageRes() == GuildTypeCalculator.RES_NOT_FOUND) {
            mGuildDescription.setVisibility(View.GONE);
            return;
        }
        mGuildDescription.setVisibility(View.VISIBLE);
        mGuildImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), resource.getImageRes()));
        mGuildText.setText(getString(resource.getGuildName()));

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
            displayResults(ManaCalculator.calculateLandsOutput(mManaCalculatorInputModel));
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

    private void initGuide(View view) {
        ChainTourGuide lands = getGuideForObject(getString(R.string.guide_title), getString(R.string.guide_description_lands), mTotalLandsTextInput);
        ChainTourGuide manaSymbols = getGuideForObject(getString(R.string.guide_title), getString(R.string.guide_description_mana), view.findViewById(R.id.calculator_input));
        mTutorialHandler = ChainTourGuide.init(getActivity()).playInSequence(createSequence(lands, manaSymbols));
        mGamePreferences.setFirstRun(false);

    }

    private Sequence createSequence(ChainTourGuide lands, ChainTourGuide manaSymbols) {
        return new Sequence.SequenceBuilder().add(lands, manaSymbols)
                .setDefaultOverlay(new Overlay()
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mTutorialHandler.next();
                            }
                        })).setContinueMethod(Sequence.ContinueMethod.OverlayListener).
                        setDefaultPointer(new Pointer())
                .build();
    }

    private ChainTourGuide getGuideForObject(String title, String description, View chainedView) {
        ToolTip toolTip = new ToolTip()
                .setTitle(title)
                .setDescription(description).setBackgroundColor(getSingleColor(R.color.colorAccent));
        return new ChainTourGuide(getActivity()).setToolTip(toolTip).playLater(chainedView);
    }

    protected int getSingleColor(int colorRes) {
        return ContextCompat.getColor(getActivity(), colorRes);
    }

    @Override
    public void onPause() {
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
            mMaterialDialog = null;
        }
        super.onPause();
    }
}

