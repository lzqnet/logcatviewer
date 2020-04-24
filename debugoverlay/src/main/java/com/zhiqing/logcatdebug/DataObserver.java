package com.zhiqing.logcatdebug;

import android.support.annotation.UiThread;

public interface DataObserver<T> {
    @UiThread
    void onDataAvailable(T data);
}
