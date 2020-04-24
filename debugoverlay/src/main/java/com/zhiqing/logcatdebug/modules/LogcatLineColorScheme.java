package com.zhiqing.logcatdebug.modules;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

public interface LogcatLineColorScheme {

    @ColorInt
    int getTextColor(LogcatLine.Priority priority, @NonNull String tag);

    LogcatLineColorScheme DEFAULT_COLOR_SCHEME = new LogcatLineColorScheme() {

        @Override
        public int getTextColor(LogcatLine.Priority priority, String tag) {
            switch (priority) {
                case VERBOSE: {
                    return Color.BLACK;
                }
                case DEBUG: {
                    return Color.BLUE;
                }
                case INFO: {
                    return Color.GREEN;
                }
                case WARNING: {
                    return Color.YELLOW;
                }
                case ERROR:
                case FATAL:
                case ASSERT: {
                    return Color.RED;
                }
                default: {
                    return Color.WHITE;
                }
            }
        }
    };
}
