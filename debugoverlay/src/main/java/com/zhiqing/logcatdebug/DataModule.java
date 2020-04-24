package com.zhiqing.logcatdebug;

import android.support.annotation.NonNull;

public interface DataModule<T> {

    void start();

    void stop();

    void notifyObservers();

    void addObserver(@NonNull DataObserver<T> observer);

    void removeObserver(@NonNull DataObserver<T> observer);
}
