package de.adrian.projectbee.data.messages;

import java.text.MessageFormat;

public enum Messages {

    //Level
    SUCCESSFULLY_SET_LEVEL("§nYou successfully set the level of player §j{0} §nto §j{1}"),
    LEVEL_ARGS_NOT_NUMBER("§hLevel must be a number!"),

    //Coins
    SUCCESSFULLY_SET_COINS("§nYou successfully set the coins of player §j{0} §nto §j{1}"),
    COINS_ARGS_NOT_NUMBER("§hCoins must be a number!"),

    //Form Messages
    PROGRESS_FORM_TITLE("§nYour Progress"),
    PROGRESS_FORM_CONTENT("§nYour Current Level §r§8» §j{0}\n§nCurrent XP §r§8» §j{2}"),

    GIVE_COSMETIC_FORM_TITLE("Give Cosmetics"),
    GIVE_COSMETIC_TYPE_FORM_CONTENT("Which type of cosmetic do you want to give the Player?"),
    GIVE_COSMETIC_ACCEPT_FORM_CONTENT("Are you really sure you want to give {0} to {1}?"),
    GIVE_COSMETIC_FORM_BUTTON("{0} ({1})"),

    SHOP_COSMETIC_FORM_TITLE("Buy Cosmetics"),
    SHOP_COSMETIC_FORM_CONTENT("You can use coins to buy Cosmetics. Click on a category below to see what we have to offer."),
    SHOP_COSMETIC_FORM_BUTTON("§n{0}"),

    VIEW_COSMETIC_FORM_TITLE("Cosmetics"),
    VIEW_COSMETIC_FORM_CONTENT("Cosmetic View form content."),
    VIEW_COSMETIC_OWNED_FORM_BUTTON("§n{0}§r §i[{1}]\n§jOwned"),
    VIEW_COSMETIC_UNOWNED_FORM_BUTTON("§n{0} §i[{1}]§r\n§hUnowned"),

    //Category Descriptions
    HAT_DESCRIPTION("A stylish accessory to wear on your head."),
    BACKBLING_DESCRIPTION("A unique item worn on your back."),
    MOUNT_DESCRIPTION("A rideable companion to travel in style."),

    //Messages
    SUCCESSFULLY_PURCHASED("§nYou successfully purchased §j{0}§n!"),
    SUCCESSFULLY_GIVEN_COSMETIC("§nYou successfully given §j{0}§n to §j{1}§n!"),
    COSMETIC_GIVING_CANCELLED("§hThe process has been cancelled."),
    NOT_ENOUGH_MONEY("§hYou dont have enough coins to buy §j{0}§h!"),
    YOU_DONT_OWN_COSMETIC("§nYou dont own §j{0}§n. §nType §j/shop§n to buy it."),
    ERROR_WHILE_CREATING_MOUNT("§hAn error occurred while spawning your mount. Please report this to the support at §nhelp.example.net"),
    SUCCESSFULLY_ACTIVATED_COSMETIC("§nYou successfully §jactivated §n{0}!"),
    SUCCESSFULLY_DEACTIVATED_COSMETIC("§nYou successfully §hdeactivated §n{0}!"),

    //Form Buttons
    YES("YES"),
    NO("NO"),

    END("This is just for me to not add a semicolon or move it when i wanna add something new :(");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String format(Object... args) {
        return MessageFormat.format(message, args);
    }
}
