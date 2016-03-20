package unii.mtg.mana.calculator.model;

/**
 * Created by Unii on 2016-03-19.
 */
public class ManaCalculatorOutputModel {
    private int mManaBlue;
    private int mManaWhite;
    private int mManaBlack;
    private int mManaRed;
    private int mManaGreen;
    private int mManaColorless;

    public ManaCalculatorOutputModel(int manaWhite, int manaBlue, int manaBlack, int manaRed, int manaGreen, int manaColorless) {
        this.mManaWhite = manaWhite;
        this.mManaBlue = manaBlue;
        this.mManaBlack = manaBlack;
        this.mManaRed = manaRed;
        this.mManaGreen = manaGreen;
        this.mManaColorless = manaColorless;
    }

    public int getManaBlue() {
        return mManaBlue;
    }

    public void setManaBlue(int mManaBlue) {
        this.mManaBlue = mManaBlue;
    }

    public int getManaWhite() {
        return mManaWhite;
    }

    public void setManaWhite(int mManaWhite) {
        this.mManaWhite = mManaWhite;
    }

    public int getManaBlack() {
        return mManaBlack;
    }

    public void setManaBlack(int mManaBlack) {
        this.mManaBlack = mManaBlack;
    }

    public int getManaRed() {
        return mManaRed;
    }

    public void setManaRed(int mManaRed) {
        this.mManaRed = mManaRed;
    }

    public int getManaGreen() {
        return mManaGreen;
    }

    public void setManaGreen(int mManaGreen) {
        this.mManaGreen = mManaGreen;
    }

    public int getManaColorless() {
        return mManaColorless;
    }

    public void setManaColorless(int mManaColorless) {
        this.mManaColorless = mManaColorless;
    }
}
