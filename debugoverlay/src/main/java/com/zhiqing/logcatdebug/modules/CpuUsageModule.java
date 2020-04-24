package com.zhiqing.logcatdebug.modules;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.zhiqing.logcatdebug.OverlayModule;
import com.zhiqing.logcatdebug.ViewModule;

/**
 * @deprecated will not work on Android O and above.
 * For details, @see <a href="https://issuetracker.google.com/issues/37140047">this issue</a>
 */
@Deprecated
public class CpuUsageModule extends OverlayModule<CpuUsage> {

    public static final int DEFAULT_INTERVAL = 1000; // 1000ms

    public CpuUsageModule() {
        super(new CpuUsageDataModule(DEFAULT_INTERVAL), new CpuUsageViewModule());
    }

    public CpuUsageModule(int interval) {
        super(new CpuUsageDataModule(interval), new CpuUsageViewModule());
    }

    public CpuUsageModule(int interval, @LayoutRes int layoutResId) {
        super(new CpuUsageDataModule(interval), new CpuUsageViewModule(layoutResId));
    }

    public CpuUsageModule(@NonNull ViewModule<CpuUsage> viewModule) {
        super(new CpuUsageDataModule(DEFAULT_INTERVAL), viewModule);
    }

    public CpuUsageModule(int interval, @NonNull ViewModule<CpuUsage> viewModule) {
        super(new CpuUsageDataModule(interval), viewModule);
    }
}