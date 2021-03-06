package com.ms_square.debugoverlay;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.google.android.libraries.cloudtesting.screenshots.ScreenShotter;
import com.zhiqing.logcatdebug.DebugOverlay;
import com.zhiqing.logcatdebug.Position;
import com.zhiqing.logcatdebug.modules.CpuUsageModule;
import com.zhiqing.logcatdebug.modules.FpsModule;
import com.zhiqing.logcatdebug.modules.LogcatLine;
import com.zhiqing.logcatdebug.modules.LogcatLineColorScheme;
import com.zhiqing.logcatdebug.modules.LogcatLineFilter;
import com.zhiqing.logcatdebug.modules.LogcatModule;
import com.zhiqing.logcatdebug.modules.MemInfoModule;
import com.ms_square.debugoverlay.sample.IPAddressModule;
import com.ms_square.debugoverlay.sample.MainActivity;
import com.ms_square.debugoverlay.sample.ScrollingActivity;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Not yet complete or good enough, but covers some common scenarios as a staring point.
 * TODO: use UiAutomation's takeScreenshot() for screen captures?
 */
abstract class DebugOverlayInstrumentedTest {

    private static final String TAG = DebugOverlayInstrumentedTest.class.getSimpleName();

    DebugOverlay debugOverlay;

    @Before
    public void setUp() {
        DebugOverlay.enableDebugLogging(true);
    }

    @After
    public void tearDown() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                if (debugOverlay != null) {
                    debugOverlay.uninstall();
                }
            }
        });
    }

    @Test
    public void useDefaultApplication() {
        assertEquals("Application", getApplication().getClass().getSimpleName());
    }

    @Test
    public void installCpuModule() throws Exception {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                debugOverlay = new DebugOverlay.Builder(getApplication())
                        .allowSystemLayer(testSystemLayer())
                        .modules(new CpuUsageModule())
                        .build();
                debugOverlay.install();
            }
        });

        waitForOverlay();
    }

    @Test
    public void installCustomModule() throws Exception {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                debugOverlay = new DebugOverlay.Builder(getApplication())
                        .allowSystemLayer(testSystemLayer())
                        .modules(new IPAddressModule(getApplication()))
                        .build();
                debugOverlay.install();
            }
        });

        waitForOverlay();
    }

    @Test
    public void installModulesTopEnd() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                debugOverlay = new DebugOverlay.Builder(getApplication())
                        .allowSystemLayer(testSystemLayer())
                        .modules(new CpuUsageModule(),
                                new MemInfoModule(getApplication()),
                                new FpsModule())
                        .position(Position.TOP_END)
                        .build();
                debugOverlay.install();
            }
        });

        waitForOverlay();
    }

    @Test
    public void installModulesBottomEnd() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                debugOverlay = new DebugOverlay.Builder(getApplication())
                        .allowSystemLayer(testSystemLayer())
                        .modules(new CpuUsageModule(),
                                new MemInfoModule(getApplication()),
                                new FpsModule())
                        .position(Position.BOTTOM_END)
                        .build();
                debugOverlay.install();
            }
        });

        waitForOverlay();
    }

    @Test
    public void installLogcatModule() {
        DebugOverlay.enableDebugLogging(false);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                debugOverlay = new DebugOverlay.Builder(getApplication())
                        .allowSystemLayer(testSystemLayer())
                        .modules(new CpuUsageModule(),
                                new MemInfoModule(getApplication()),
                                new FpsModule(),
                                new LogcatModule(10))
                        .position(Position.BOTTOM)
                        .build();
                debugOverlay.install();
            }
        });

        waitForOverlay();

        Log.v(TAG, "hello world V");
        Log.d(TAG, "hello world D");

        Log.i(TAG, "hello world I");
        Log.w(TAG, "hello world W");

        Log.e(TAG, "hello world E");
        Log.wtf(TAG, "hello world WTF");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DebugOverlay.enableDebugLogging(true);
    }

    @Test
    public void installLogcatModuleWCustomColor() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                debugOverlay = new DebugOverlay.Builder(getApplication())
                        .allowSystemLayer(testSystemLayer())
                        .modules(new LogcatModule(10, new LogcatLineColorScheme() {
                            @Override
                            public int getTextColor(LogcatLine.Priority priority, @NonNull String tag) {
                                return Color.CYAN;
                            }
                        }))
                        .build();
                debugOverlay.install();
            }
        });

        waitForOverlay();
    }

    @Test
    public void installLogcatModuleWCustomFilter() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                debugOverlay = new DebugOverlay.Builder(getApplication())
                        .allowSystemLayer(testSystemLayer())
                        .modules(new LogcatModule(10, new LogcatLineFilter.SimpleLogcatLineFilter(LogcatLine.Priority.INFO)))
                        .build();
                debugOverlay.install();
            }
        });

        waitForOverlay();
    }

    @Test
    public void installModulesCustomStyle() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                debugOverlay = new DebugOverlay.Builder(getApplication())
                        .allowSystemLayer(testSystemLayer())
                        .modules(new CpuUsageModule(),
                                new MemInfoModule(getApplication()),
                                new FpsModule())
                        .position(Position.TOP_START)
                        .bgColor(Color.parseColor("#60000000"))
                        .textColor(Color.MAGENTA)
                        .textSize(14f)
                        .textAlpha(.8f)
                        .build();
                debugOverlay.install();
            }
        });

        waitForOverlay();
    }


    @Test
    public void startSecondActivity() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                debugOverlay = new DebugOverlay.Builder(getApplication())
                        .allowSystemLayer(testSystemLayer())
                        .build();
                debugOverlay.install();
            }
        });

        waitForOverlay();

        Instrumentation.ActivityMonitor monitor = new Instrumentation.ActivityMonitor(ScrollingActivity.class.getCanonicalName(),
                null, false);
        getInstrumentation().addMonitor(monitor);
        try {
            onView(withId(com.ms_square.debugoverlay.sample.R.id.fab)).perform(click());
            Activity nextActivity = monitor.waitForActivityWithTimeout(5000);
            assertThat(nextActivity, Matchers.is(Matchers.notNullValue()));

            takeActivityScreenShot(nextActivity);

            nextActivity.finish();
        } finally {
            getInstrumentation().removeMonitor(monitor);
        }
    }

    abstract ActivityTestRule getActivityRule();

    abstract boolean testSystemLayer();

    void waitForOverlay() {
        waitForOverlay(5000);
    }

    void waitForOverlay(long millis) {
        if (!testSystemLayer()) {
            Instrumentation.ActivityMonitor monitor = new Instrumentation.ActivityMonitor(MainActivity.class.getCanonicalName(),
                    null, false);
            getInstrumentation().addMonitor(monitor);
            getActivityRule().launchActivity(new Intent(getApplication(), MainActivity.class));
            try {
                monitor.waitForActivityWithTimeout(5000);
            } finally {
                getInstrumentation().removeMonitor(monitor);
            }
        }
        try {
            Thread.currentThread().sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Instrumentation getInstrumentation() {
        return InstrumentationRegistry.getInstrumentation();
    }

    Application getApplication() {
        return (Application) getInstrumentation().getTargetContext().getApplicationContext();
    }

    void takeActivityScreenShot(Activity activity) {
        // This only captures the main application window. See captured videos in FireBase to check
        // what's being displayed in the overlay.
        // Note that test method name is automatically captured and appended by the lib
        ScreenShotter.takeScreenshot(getClass().getSimpleName(), activity);
    }
}
