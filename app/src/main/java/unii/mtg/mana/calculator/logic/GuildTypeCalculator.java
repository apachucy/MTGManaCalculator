package unii.mtg.mana.calculator.logic;


import unii.mtg.mana.calculator.R;
import unii.mtg.mana.calculator.model.Guild;
import unii.mtg.mana.calculator.model.ManaCalculatorOutputModel;

public class GuildTypeCalculator {
    public static final int RES_NOT_FOUND = -1;

    GuildTypeCalculator() {
    }

    public static Guild calculateImageResource(ManaCalculatorOutputModel colors) {
        Guild selectedGuild = new Guild();
        if (isWhite(colors) && isGreen(colors) && !isRed(colors) && !isBlack(colors) && !isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_wg_selensnya);
            selectedGuild.setGuildName(R.string.guild_selesnya);
        } else if (isWhite(colors) && isGreen(colors) && !isRed(colors) && isBlack(colors) && !isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_wbg_abzan);
            selectedGuild.setGuildName(R.string.guild_abzan);
        } else if (isWhite(colors) && !isGreen(colors) && isRed(colors) && !isBlack(colors) && !isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_wr_boros);
            selectedGuild.setGuildName(R.string.guild_boros);
        } else if (isWhite(colors) && !isGreen(colors) && !isRed(colors) && isBlack(colors) && !isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_wb_orzhov);
            selectedGuild.setGuildName(R.string.guild_orzhov);
        } else if (isWhite(colors) && !isGreen(colors) && !isRed(colors) && !isBlack(colors) && isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_wu_azorius);
            selectedGuild.setGuildName(R.string.guild_azorius);
        } else if (isWhite(colors) && !isGreen(colors) && isRed(colors) && isBlack(colors) && !isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_rbw_mardu);
            selectedGuild.setGuildName(R.string.guild_mardu);
        } else if (isWhite(colors) && !isGreen(colors) && isRed(colors) && !isBlack(colors) && isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_wru_jeskai);
            selectedGuild.setGuildName(R.string.guild_jeskai);
        } else if (!isWhite(colors) && isGreen(colors) && !isRed(colors) && !isBlack(colors) && isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_wg_simic);
            selectedGuild.setGuildName(R.string.guild_simic);
        } else if (!isWhite(colors) && isGreen(colors) && !isRed(colors) && isBlack(colors) && !isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_gb_golgari);
            selectedGuild.setGuildName(R.string.guild_golgari);
        } else if (!isWhite(colors) && isGreen(colors) && isRed(colors) && !isBlack(colors) && !isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_gr_gruul);
            selectedGuild.setGuildName(R.string.guild_gruul);
        } else if (!isWhite(colors) && isGreen(colors) && isRed(colors) && !isBlack(colors) && isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_gru_temur);
            selectedGuild.setGuildName(R.string.guild_temur);
        } else if (!isWhite(colors) && isGreen(colors) && !isRed(colors) && isBlack(colors) && isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_ubg_sultai);
            selectedGuild.setGuildName(R.string.guild_sultai);
        } else if (!isWhite(colors) && !isGreen(colors) && isRed(colors) && !isBlack(colors) && isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_ru_izzet);
            selectedGuild.setGuildName(R.string.guild_izzet);
        } else if (!isWhite(colors) && !isGreen(colors) && isRed(colors) && isBlack(colors) && !isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_rb_rakdos);
            selectedGuild.setGuildName(R.string.guild_rakdos);
        } else if (!isWhite(colors) && !isGreen(colors) && !isRed(colors) && isBlack(colors) && isBlue(colors)) {
            selectedGuild.setImageRes(R.mipmap.ic_ub_dimir);
            selectedGuild.setGuildName(R.string.guild_dimir);
        } else {
            selectedGuild.setImageRes(RES_NOT_FOUND);
            selectedGuild.setGuildName(RES_NOT_FOUND);
        }
        return selectedGuild;
    }


    private static boolean isRed(ManaCalculatorOutputModel colors) {
        return colors.getManaRed() > 0;
    }

    private static boolean isBlack(ManaCalculatorOutputModel colors) {
        return colors.getManaBlack() > 0;
    }

    private static boolean isGreen(ManaCalculatorOutputModel colors) {
        return colors.getManaGreen() > 0;
    }

    private static boolean isBlue(ManaCalculatorOutputModel colors) {
        return colors.getManaBlue() > 0;
    }

    private static boolean isWhite(ManaCalculatorOutputModel colors) {
        return colors.getManaWhite() > 0;
    }
}

