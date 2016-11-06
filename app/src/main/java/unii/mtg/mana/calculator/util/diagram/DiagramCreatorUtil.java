package unii.mtg.mana.calculator.util.diagram;


import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.echo.holographlibrary.Bar;

import java.util.ArrayList;

import unii.mtg.mana.calculator.R;
import unii.mtg.mana.calculator.model.ManaCalculatorInputModel;
import unii.mtg.mana.calculator.model.ManaCalculatorOutputModel;

public class DiagramCreatorUtil {

    private DiagramCreatorUtil() {
    }

    public static ArrayList<Bar> createDataForDiagram(Context context, ManaCalculatorOutputModel manaCalculatorOutputModel, ManaCalculatorInputModel manaCalculatorInputModel) {
        ArrayList<Bar> manaBarList = new ArrayList<Bar>();
        if (manaCalculatorInputModel.getLandTotal() == 0 || manaCalculatorInputModel.getManaTotal() == 0) {
            return manaBarList;
        }

        if (manaCalculatorOutputModel.getManaBlack() != 0) {
            manaBarList.add(createManaBar(context.getString(R.string.mana_calculator_hint_land_black), manaCalculatorOutputModel.getManaBlack(), getColor(context, R.color.colorBlack)));
        }
        if (manaCalculatorOutputModel.getManaBlue() != 0) {
            manaBarList.add(createManaBar(context.getString(R.string.mana_calculator_hint_land_blue), manaCalculatorOutputModel.getManaBlue(), getColor(context, R.color.colorBlue)));
        }

        if (manaCalculatorOutputModel.getManaRed() != 0) {
            manaBarList.add(createManaBar(context.getString(R.string.mana_calculator_hint_land_red), manaCalculatorOutputModel.getManaRed(), getColor(context, R.color.colorRed)));
        }
        if (manaCalculatorOutputModel.getManaWhite() != 0) {
            manaBarList.add(createManaBar(context.getString(R.string.mana_calculator_hint_land_white), manaCalculatorOutputModel.getManaWhite(), getColor(context, R.color.colorWhite)));
        }
        if (manaCalculatorOutputModel.getManaGreen() != 0) {
            manaBarList.add(createManaBar(context.getString(R.string.mana_calculator_hint_land_green), manaCalculatorOutputModel.getManaGreen(), getColor(context, R.color.colorGreen)));
        }
        if (manaCalculatorOutputModel.getManaColorless() != 0) {
            manaBarList.add(createManaBar(context.getString(R.string.mana_calculator_hint_land_colorless), manaCalculatorOutputModel.getManaColorless(), getColor(context, R.color.colorColorless)));
        }
        return manaBarList;


    }
    private static int getColor(Context context, int color) {
        return ContextCompat.getColor(context, color);
    }
    private static Bar createManaBar(String barName, int value, int barColor) {
        Bar manaBar = new Bar();
        manaBar.setName(barName);
        manaBar.setValue(value);
        manaBar.setColor(barColor);
        return manaBar;
    }
}
