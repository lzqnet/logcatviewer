package com.ms_square.debugoverlay_ext_timber;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

import com.zhiqing.logcatdebug.OverlayModule;
import com.zhiqing.logcatdebug.ViewModule;
import com.zhiqing.logcatdebug.modules.LogcatLine;
import com.zhiqing.logcatdebug.modules.LogcatLineColorScheme;
import com.zhiqing.logcatdebug.modules.LogcatLineFilter;
import com.zhiqing.logcatdebug.modules.LogcatViewModule;

public class TimberModule extends OverlayModule<LogcatLine> {

    public static final int DEFAULT_MAX_LINES = 15;

    /**
     * Pass BuildConfig.DEBUG not to plant a tree in the release build.
     * @param isDebug - should be your BuildConfig.DEBUG
     */
    public TimberModule(boolean isDebug) {
        super(new TimberDataModule(isDebug), new LogcatViewModule(DEFAULT_MAX_LINES));
    }

    public TimberModule(boolean isDebug, @Size(min=1,max=100) int maxLines) {
        super(new TimberDataModule(isDebug), new LogcatViewModule(maxLines));
    }

    public TimberModule(boolean isDebug, @Size(min=1,max=100) int maxLines, @NonNull LogcatLineFilter lineFilter) {
        super(new TimberDataModule(isDebug), new LogcatViewModule(maxLines, lineFilter));
    }

    public TimberModule(boolean isDebug, @Size(min=1,max=100) int maxLines, @NonNull LogcatLineColorScheme colorScheme) {
        super(new TimberDataModule(isDebug), new LogcatViewModule(maxLines, colorScheme));
    }

    public TimberModule(boolean isDebug, @Size(min=1,max=100) int maxLines, @NonNull LogcatLineFilter lineFilter,
                        @NonNull LogcatLineColorScheme colorScheme) {
        super(new TimberDataModule(isDebug), new LogcatViewModule(maxLines, lineFilter, colorScheme));
    }

    public TimberModule(boolean isDebug, @NonNull ViewModule<LogcatLine> viewModule) {
        super(new TimberDataModule(isDebug), viewModule);
    }
}