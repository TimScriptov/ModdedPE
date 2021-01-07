package com.microsoft.xbox.service.notification;

import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.mcal.mcpelauncher.R;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * 07.01.2021
 *
 * @author Тимашков Иван
 * @author https://github.com/TimScriptov
 */

public class NotificationResult {
    public String body;
    public String data;
    public NotificationType notificationType;
    public String title;

    public NotificationResult(@NotNull RemoteMessage remoteMessage, Context context) {
        Map data2 = remoteMessage.getData();
        String str = (String) data2.get("type");
        if (str == null) {
            this.notificationType = NotificationType.Unknown;
        } else if (str.equals("xbox_live_game_invite")) {
            this.notificationType = NotificationType.Invite;
            this.title = context.getString(R.string.xbox_live_game_invite_title);
            String string = context.getString(R.string.xbox_live_game_invite_body);
            RemoteMessage.Notification notification = remoteMessage.getNotification();
            if (notification != null) {
                Log.i("XSAPI.Android", "parsing notification");
                String[] bodyLocalizationArgs = notification.getBodyLocalizationArgs();
                if (bodyLocalizationArgs != null) {
                    this.body = String.format(string, bodyLocalizationArgs[0], bodyLocalizationArgs[1]);
                }
            } else {
                Log.i("XSAPI.Android", "could not parse notification");
            }
        } else if (str.equals("xbox_live_achievement_unlock")) {
            this.notificationType = NotificationType.Achievement;
            if (remoteMessage.getNotification() != null) {
                this.title = remoteMessage.getNotification().getTitle();
                this.body = remoteMessage.getNotification().getBody();
            }
        } else {
            this.notificationType = NotificationType.Unknown;
        }
        this.data = (String) data2.get("xbl");
    }

    public enum NotificationType {
        Achievement,
        Invite,
        Unknown
    }
}
