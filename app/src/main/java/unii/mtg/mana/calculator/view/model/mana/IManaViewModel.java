package unii.mtg.mana.calculator.view.model.mana;


public interface IManaViewModel {
    void onDestroy();

    void setMaxValue( int newMaxValue);

    int getCurrentValue();

}
