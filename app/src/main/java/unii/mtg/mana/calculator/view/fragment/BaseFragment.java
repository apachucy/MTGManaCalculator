package unii.mtg.mana.calculator.view.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;

import unii.mtg.mana.calculator.R;

public class BaseFragment extends Fragment {


    protected void showInputDialog(Context context, String title, String content, String hint, String lastValue, MaterialDialog.InputCallback inputCallback) {
        new MaterialDialog.Builder(context).
                title(title).content(content).inputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED)
                .backgroundColorRes(R.color.windowBackground).input(hint, lastValue, inputCallback).
                show();
    }


    protected void showInfoDialog(Context context, String title, String content, String positiveButtonText) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(content).backgroundColorRes(R.color.windowBackground)
                .positiveText(positiveButtonText)
                .show();
    }
}
