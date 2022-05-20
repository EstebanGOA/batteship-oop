package presentation.views;

public enum SpritePath {

    // ************************* BOARD SPRITES *************************
    WATER("sprites/GameViews/water.png"),
    HIT("sprites/GameViews/explosion.png"),
    MISS("sprites/GameViews/miss.png"),
    WATER_HOVER("sprites/GameViews/water_hover.png"),

    // ************************* SHIPS SPRITES *************************
    BOAT("sprites/GameViews/SetupStageView/rotated_boat.png"),
    SUBMARINE("sprites/GameViews/SetupStageView/rotated_submarine.png"),
    AIRCRAFT("sprites/GameViews/SetupStageView/rotated_aircraft.png"),
    DESTRUCTOR("sprites/GameViews/SetupStageView/rotated_destructor.png"),


    // ************************* BACKGROUND SPRITES *************************
    SHIP_PANEL_BACKGROUND("sprites/ship_panel.png"),
    PLAYER_SHIPS_BACKGROUND("sprites/GameViews/your_ships_panel.png"),
    SHIP_PANEL_BACKGROUND_HOVER("sprites/ship_panel_hover.png"),
    SECONDARY_BACKGROUND("sprites/GameViews/SetupStageView/bg2_panel.png"),
    LOGIN_BACKGROUND("sprites/login_background_v2.png"),
    MENU_BACKGROUND("sprites/background_main_menu_v2.png"),
    ENEMY_BACKGROUND("sprites/enemyBg.png"),
    TROPHIES_BACKGROUND("sprites/trophies_background.png"),

    // ************************* BUTTONS SPRITES *************************
    ROTATE_BUTTON("sprites/GameViews/SetupStageView/bg_rotate_btn.png"),
    ROTATE_BUTTON_SELECTED("sprites/GameViews/SetupStageView/bg_rotate_btn_selected.png"),
    ROTATE_ARROW_ICON("sprites/GameViews/SetupStageView/rotated_arrow.png"),

    ATTACK_BUTTON("sprites/GameViews/SetupStageView/start_attack_bg.png"),
    ATTACK_BUTTON_SELECTED("sprites/GameViews/SetupStageView/start_game_selected.png"),
    ATTACK_BUTTON_ICON("sprites/GameViews/SetupStageView/attack_icon.png"),
    END_BATTLE_BACKGROUND("sprites/endBattleBg.png"),
    END_BATTLE_BUTTON("sprites/endBattleBtn.png"),
    END_BATTLE_BUTTON_HOVER("sprites/endBattleBtn_hover.png"),
    USER_SELECTED_ICON("sprites/GameViews/SetupStageView/full_user.png"),
    USER_EMPTY_ICON("sprites/GameViews/SetupStageView/empty_user.png"),
    USER_HOVER_ICON("sprites/GameViews/SetupStageView/full_ico_user_50%.png"),

    //
    LOGIN_BUTTON("sprites/login_button.png"),
    LOGIN_BUTTON_HOVER("sprites/login_button_hover.png"),
    PASSWORD_ICON("sprites/lock_ico.png"),
    EMAIL_ICON("sprites/email_ico.png"),
    NEW_BATTLE_BUTTON("sprites/new_battle_button.png"),
    LOAD_BATTLE_BUTTON("sprites/load_battle_button.png"),
    SETTINGS_BUTTON("sprites/settings_button.png"),
    STATISTICS_BUTTON("sprites/statistics_button.png"),
    REGISTER_BUTTON("sprites/register_button.png"),
    USER_ICON("sprites/user_ico.png"),
    BACK_BUTTON("sprites/back_button.png"),
    DELETE_ACCOUNT_BUTTON("sprites/delete_account_button.png"),
    LOGOUT_BUTTON("sprites/logout_button.png"),

    //
    TITLE_MENU("sprites/name_background.png");

    private final String path;

    SpritePath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
