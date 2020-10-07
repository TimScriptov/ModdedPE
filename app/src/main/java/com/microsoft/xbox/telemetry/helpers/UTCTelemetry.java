package com.microsoft.xbox.telemetry.helpers;

import com.microsoft.xbox.idp.ui.ErrorActivity;
import com.microsoft.xbox.telemetry.utc.CommonData;
import com.microsoft.xbox.telemetry.utc.model.UTCNames;

public class UTCTelemetry {
    public static final String UNKNOWNPAGE = "Unknown";

    private static native void WriteEvent(String str);

    public static void LogEvent(CommonData event) {
        WriteEvent(event.ToJson());
    }

    public static String getErrorScreen(ErrorActivity.ErrorScreen errorScreen) {
        switch (errorScreen) {
            case BAN:
                return UTCNames.PageView.Errors.Banned;
            case CATCHALL:
                return UTCNames.PageView.Errors.Generic;
            case CREATION:
                return UTCNames.PageView.Errors.Create;
            case OFFLINE:
                return UTCNames.PageView.Errors.Offline;
            default:
                return String.format("%sErrorScreen", new Object[]{UNKNOWNPAGE});
        }
    }

    public enum CallBackSources {
        Account,
        Ticket
    }
}
