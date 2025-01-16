package de.adrian.projectbee.data.messages;

import java.text.MessageFormat;

public enum Messages {

    //Level
    SUCCESSFULLY_SET_LEVEL("§eYou successfully set the level of player §6{0} §eto §6{1}"),
    LEVEL_ARGS_NOT_NUMBER("§cLevel must be a number!"),

    //Form Messages
    PROGRESS_FORM_TITLE("§eYour Progress"),
    PROGRESS_FORM_CONTENT("§eYour Current Level §r§8» §6{0}\n§eCurrent XP §r§8» §6{1}"),

    GIVE_COSMETIC_FORM_TITLE("§eGive Cosmetics"),
    GIVE_COSMETIC_FORM_COSMETIC_DROPDOWN_TITLE("Which cosmetic do you want to give the User"),
    GIVE_COSMETIC_FORM_2(""),

    //Form Buttons
    CLOSE("Close"),
    YES("YES"),
    NO("NO"),
    ACCEPT("Accept"),
    DECLINE("Decline"),
    OK("Ok"),

    END("END");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String format(Object... args) {
        return MessageFormat.format(message, args);
    }
}
