package com.ms_square.debugoverlay.sample;

import android.app.Application;

import android.graphics.Color;
import com.zhiqing.logcatdebug.DebugOverlay;
import com.zhiqing.logcatdebug.Position;
import com.zhiqing.logcatdebug.modules.FpsModule;
import com.zhiqing.logcatdebug.modules.LogcatLine;
import com.zhiqing.logcatdebug.modules.LogcatModule;
import com.zhiqing.logcatdebug.modules.TagAndPriorityFilter;
import com.squareup.leakcanary.LeakCanary;

public class DebugOverlaySampleApplication extends Application {

    private static final String TAG = DebugOverlaySampleApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        // Simplest way to use
       // DebugOverlay.with(this).install();

        // Fully Customize
        DebugOverlay.installDefault(this,"lzqtest");
        //new DebugOverlay.Builder(this)
        //        .modules(//new CpuUsageModule(),
        //                //new MemInfoModule(this),
        //                new FpsModule(),
        //                new LogcatModule(15,  new TagAndPriorityFilter(LogcatLine.Priority.VERBOSE,"lzqtest")))
        //        .position(Position.BOTTOM_START)
        //        .bgColor(Color.parseColor("#30000000"))
        //        .textColor(Color.MAGENTA)
        //        .textSize(14f)
        //        .textAlpha(.8f)
        //        .allowSystemLayer(true)
        //        .notification(true, MainActivity.class.getName())
        //        .build()
        //        .install();

        // Use custom module
//        new DebugOverlay.Builder(this)
//                .modules(new CpuFreqModule(),
//                        new CpuUsageModule(),
//                        new MemInfoModule(this),
//                        new FpsModule(),
//                        new IPAddressModule(this),
//                        new NetStatsModule(),
//                        new TimberModule(BuildConfig.DEBUG))
//                .build()
//                .install();

        // Enable debug logging of DebugOverlay for its development.
        // You do not need to enable this if you use this library without any further customization.
        //DebugOverlay.enableDebugLogging(true);
    }
}
