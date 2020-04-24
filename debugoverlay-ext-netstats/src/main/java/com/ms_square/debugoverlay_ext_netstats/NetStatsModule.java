package com.ms_square.debugoverlay_ext_netstats;

import com.zhiqing.logcatdebug.OverlayModule;
import com.zhiqing.logcatdebug.modules.SimpleViewModule;

public class NetStatsModule extends OverlayModule<String> {

    private static final int DEFAULT_INTERVAL = 1000; // ms

    public NetStatsModule() {
        this(DEFAULT_INTERVAL);
    }

    public NetStatsModule(int interval) {
        super(new NetStatsDataModule(interval), new SimpleViewModule(R.layout.debugoverlay_netstats));
    }
}
