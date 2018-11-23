package com.i4games.dogfight.util;

public class ImageNames {
    private static final ImageNames ourInstance = new ImageNames();

    public static ImageNames getInstance() {
        return ourInstance;
    }

    private ImageNames() {
    }

    public final String splashScreenLogo = "ifourgames.png";
}
