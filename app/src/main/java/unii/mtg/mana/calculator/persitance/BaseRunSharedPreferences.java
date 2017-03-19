package unii.mtg.mana.calculator.persitance;

import android.content.Context;
import android.content.SharedPreferences;

public class BaseRunSharedPreferences implements IBaseRunPreferences {

    private SharedPreferences mSharedPreferences;
    private static final String SHARED_PREFERENCES_NAME = "baseRunSharedPreferences";
    private static final String IS_APP_FIRST_RUN = BaseRunSharedPreferences.class.toString() + "IS_APP_FIRST_RUN";

    public BaseRunSharedPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

    }

    @Override
    public boolean isFirstRun() {
        return mSharedPreferences.getBoolean(IS_APP_FIRST_RUN, true);
    }

    @Override
    public void setFirstRun(boolean isFirstRun) {
        mSharedPreferences.edit().putBoolean(IS_APP_FIRST_RUN,isFirstRun).apply();
    }
}
