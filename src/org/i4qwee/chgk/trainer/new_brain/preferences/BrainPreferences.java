package org.i4qwee.chgk.trainer.new_brain.preferences;

import java.util.prefs.Preferences;

/**
 * User: 4qwee
 * Date: 24.10.12
 * Time: 16:36
 */
public class BrainPreferences
{
    private static final Preferences preferences;

    static
    {
        preferences = Preferences.userRoot().node(PreferencesNames.NODE_NAME);
    }

    public static Preferences getPreferences()
    {
        return preferences;
    }
}
