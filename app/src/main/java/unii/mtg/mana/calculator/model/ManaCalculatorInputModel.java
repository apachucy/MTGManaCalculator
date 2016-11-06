package unii.mtg.mana.calculator.model;

public class ManaCalculatorInputModel {
    private int mLandTotal;
    private int mManaTotal;
    private int mManaBlue;
    private int mManaWhite;
    private int mManaBlack;
    private int mManaRed;
    private int mManaGreen;
    private int mManaColorless;

    public ManaCalculatorInputModel(int landTotal, int manaTotal, int manaBlue, int manaWhite, int manaBlack, int manaRed, int manaGreen, int manaColorless) {
        this.mLandTotal = landTotal;
        this.mManaTotal = manaTotal;
        this.mManaBlue = manaBlue;
        this.mManaWhite = manaWhite;
        this.mManaRed = manaRed;
        this.mManaGreen = manaGreen;
        this.mManaBlack = manaBlack;
    }

    public ManaCalculatorInputModel() {
        this.mLandTotal = 0;
        this.mManaTotal = 0;
        this.mManaBlue = 0;
        this.mManaWhite = 0;
        this.mManaRed = 0;
        this.mManaGreen = 0;
        this.mManaBlack = 0;
    }

    public int getLandTotal() {
        return mLandTotal;
    }

    public void setLandTotal(int mLandTotal) {
        this.mLandTotal = mLandTotal;
    }

    public int getManaTotal() {
        return mManaTotal;
    }

    public void setManaTotal(int mManaTotal) {
        this.mManaTotal = mManaTotal;
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

    public void setManaColorless(int mColorlessMana) {
        this.mManaColorless = mColorlessMana;
    }

    public void calculateTotalMana() {
        mManaTotal = mManaBlack + mManaRed + mManaBlue + mManaWhite + mManaGreen + mManaColorless;
    }
}
