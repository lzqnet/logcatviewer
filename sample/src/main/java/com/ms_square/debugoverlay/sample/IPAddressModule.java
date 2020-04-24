package com.ms_square.debugoverlay.sample;

import android.content.Context;
import android.support.annotation.NonNull;

import com.zhiqing.logcatdebug.OverlayModule;
import com.zhiqing.logcatdebug.modules.SimpleViewModule;

public class IPAddressModule extends OverlayModule<String> {

    public IPAddressModule(@NonNull Context context) {
        super(new IPAddressDataModule(context), new SimpleViewModule(R.layout.view_overlay_ip));
    }
}
