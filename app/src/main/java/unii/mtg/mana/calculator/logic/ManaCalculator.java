package unii.mtg.mana.calculator.logic;

import unii.mtg.mana.calculator.model.ManaCalculatorInputModel;
import unii.mtg.mana.calculator.model.ManaCalculatorOutputModel;


public final class ManaCalculator {
    private ManaCalculator() {
    }

    public static ManaCalculatorOutputModel calculateLandsOutput(ManaCalculatorInputModel manaCalculatorInputModel) {
        int landsWhite = calculateLandOutput(manaCalculatorInputModel.getManaWhite(), manaCalculatorInputModel.getManaTotal(), manaCalculatorInputModel.getLandTotal());
        int landsBlue = calculateLandOutput(manaCalculatorInputModel.getManaBlue(), manaCalculatorInputModel.getManaTotal(), manaCalculatorInputModel.getLandTotal());
        int landsBlack = calculateLandOutput(manaCalculatorInputModel.getManaBlack(), manaCalculatorInputModel.getManaTotal(), manaCalculatorInputModel.getLandTotal());
        int landsRed = calculateLandOutput(manaCalculatorInputModel.getManaRed(), manaCalculatorInputModel.getManaTotal(), manaCalculatorInputModel.getLandTotal());
        int landsGreen = calculateLandOutput(manaCalculatorInputModel.getManaGreen(), manaCalculatorInputModel.getManaTotal(), manaCalculatorInputModel.getLandTotal());
        int landsColorless = calculateLandOutput(manaCalculatorInputModel.getManaColorless(), manaCalculatorInputModel.getManaTotal(), manaCalculatorInputModel.getLandTotal());

        return new ManaCalculatorOutputModel(landsWhite, landsBlue, landsBlack, landsRed, landsGreen, landsColorless);
    }

    public static int calculateLandOutput(int manaInSpecificColor, int totalMana, int totalLands) {
        if (totalMana > 0) {
            float total = manaInSpecificColor * totalLands;
            total /= totalMana;
            return Math.round(total);
        } else {
            return 0;
        }
    }

    public static int calculateTotalManaCost(ManaCalculatorInputModel manaCalculatorInputModel) {
        return manaCalculatorInputModel.getManaWhite() + manaCalculatorInputModel.getManaBlue() + manaCalculatorInputModel.getManaBlack() + manaCalculatorInputModel.getManaRed() + manaCalculatorInputModel.getManaGreen() + manaCalculatorInputModel.getManaColorless();
    }
}
