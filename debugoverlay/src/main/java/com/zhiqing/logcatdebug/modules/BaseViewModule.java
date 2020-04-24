package com.zhiqing.logcatdebug.modules;

import android.support.annotation.LayoutRes;

import com.zhiqing.logcatdebug.ViewModule;

public abstract class BaseViewModule<T> implements ViewModule<T> {

    @LayoutRes
    protected int layoutResId;

    public BaseViewModule(@LayoutRes int layoutResId) {
        this.layoutResId = layoutResId;
    }
}
