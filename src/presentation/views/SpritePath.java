package presentation.views;

/**
 * Enumeration SpritePath used to get all the images needed.
 */
public enum SpritePath {

    // ************************* BOARD SPRITES *************************

    /**
     * Water sprite path.
     */
    WATER("sprites/GameViews/water.png"),

    /**
     * Boat ship path.
     */
    BOAT("sprites/GameViews/SetupStageView/rotated_boat.png"),

    /**
     * Submarine ship path.
     */
    SUBMARINE("sprites/GameViews/SetupStageView/rotated_submarine.png"),

    /**
     * Aircraft ship path.
     */
    AIRCRAFT("sprites/GameViews/SetupStageView/rotated_aircraft.png"),

    /**
     * Destructor ship path.
     */
    DESTRUCTOR("sprites/GameViews/SetupStageView/rotated_destructor.png"),

    // ************************* BACKGROUND SPRITES *************************

    /**
     * Ship panel background image path.
     */
    SHIP_PANEL_BACKGROUND("sprites/ship_panel.png"),

    /**
     * Player ships background image path.
     */
    PLAYER_SHIPS_BACKGROUND("sprites/GameViews/your_ships_panel.png"),

    /**
     * Ship panel background hover path.
     */
    SHIP_PANEL_BACKGROUND_HOVER("sprites/ship_panel_hover.png"),

    /**
     * Secondary background path for the ship preview and for the number of enemies.
     */
    SECONDARY_BACKGROUND("sprites/GameViews/SetupStageView/bg2_panel.png"),

    /**
     * Login background path.
     */
    LOGIN_BACKGROUND("sprites/login_background_v2.png"),

    /**
     * Menu background path.
     */
    MENU_BACKGROUND("sprites/background_main_menu_v2.png"),

    /**
     * Enemy background path.
     */
    ENEMY_BACKGROUND("sprites/enemyBg.png"),

    /**
     * Background trophies path.
     */
    TROPHIES_BACKGROUND("sprites/trophies_background.png"),

    // ************************* BUTTONS SPRITES *************************

    /**
     * Rotate button background.
     */
    ROTATE_BUTTON("sprites/GameViews/SetupStageView/bg_rotate_btn.png"),

    /**
     * Rotate button selected background path.
     */
    ROTATE_BUTTON_SELECTED("sprites/GameViews/SetupStageView/bg_rotate_btn_selected.png"),

    /**
     * Arrow icon for the rotate button.
     */
    ROTATE_ARROW_ICON("sprites/GameViews/SetupStageView/rotated_arrow.png"),

    /**
     * Attack button path.
     */
    ATTACK_BUTTON("sprites/GameViews/SetupStageView/start_attack_bg.png"),

    /**
     * Attack button when selected.
     */
    ATTACK_BUTTON_SELECTED("sprites/GameViews/SetupStageView/start_game_selected.png"),

    /**
     * Icon for the attack button.
     */
    ATTACK_BUTTON_ICON("sprites/GameViews/SetupStageView/attack_icon.png"),

    /**
     * End battle button background.
     */
    END_BATTLE_BACKGROUND("sprites/endBattleBg.png"),

    /**
     * End battle button
     */
    END_BATTLE_BUTTON("sprites/endBattleBtn.png"),

    /**
     * End battle button when is hover.
     */
    END_BATTLE_BUTTON_HOVER("sprites/endBattleBtn_hover.png"),

    /**
     * User selected icon path.
     */
    USER_SELECTED_ICON("sprites/GameViews/SetupStageView/full_user.png"),

    /**
     * User empty icon.
     */
    USER_EMPTY_ICON("sprites/GameViews/SetupStageView/empty_user.png"),

    /**
     * User icon hover.
     */
    USER_HOVER_ICON("sprites/GameViews/SetupStageView/full_ico_user_50%.png"),

    /**
     * Login button path.
     */
    LOGIN_BUTTON("sprites/login_button.png"),

    /**
     * Login button hover path.
     */
    LOGIN_BUTTON_HOVER("sprites/login_button_hover.png"),

    /**
     * Lock icon path.
     */
    PASSWORD_ICON("sprites/lock_ico.png"),

    /**
     * Email icon path.
     */
    EMAIL_ICON("sprites/email_ico.png"),

    /**
     * New battle button sprite path.
     */
    NEW_BATTLE_BUTTON("sprites/new_battle_button.png"),

    /**
     * Load battle button sprite path.
     */
    LOAD_BATTLE_BUTTON("sprites/load_battle_button.png"),

    /**
     * Settings button sprite path.
     */
    SETTINGS_BUTTON("sprites/settings_button.png"),

    /**
     * Statistics button path.
     */
    STATISTICS_BUTTON("sprites/statistics_button.png"),

    /**
     * Register button path.
     */
    REGISTER_BUTTON("sprites/register_button.png"),

    /**
     * User icon path.
     */
    USER_ICON("sprites/user_ico.png"),

    /**
     * Back button icon sprite path.
     */
    BACK_BUTTON("sprites/back_button.png"),

    /**
     * Delete account button sprite path.
     */
    DELETE_ACCOUNT_BUTTON("sprites/delete_account_button.png"),

    /**
     * Logout button sprite path.
     */
    LOGOUT_BUTTON("sprites/logout_button.png"),

    // *********************** USER PLAYER NAME BACKGROUND *****************************

    /**
     * Player name background.
     */
    TITLE_MENU("sprites/name_background.png");

    private final String path;
    

    /**
     *
     * Constructor of the sprite path.
     *
     * @param path path of the image.
     *
     */

    SpritePath(String path) {
        this.path = path;

    }



    /**
     *
     * Method that return the path as string.
     *
     * @return the path of the image.
     *
     */

    @Override
    public String toString() {
        return path;
    }



}
