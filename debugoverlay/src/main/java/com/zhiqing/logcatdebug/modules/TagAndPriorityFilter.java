package com.zhiqing.logcatdebug.modules;

import android.support.annotation.NonNull;
import android.text.TextUtils;

public class TagAndPriorityFilter implements LogcatLineFilter {
    private final LogcatLine.Priority minPriority;
    private final String mTag;

    public TagAndPriorityFilter(LogcatLine.Priority minPriority,String mTag) {
        this.minPriority = minPriority;
        this.mTag = mTag;
    }

    @Override
    public boolean shouldFilterOut(LogcatLine.Priority priority, @NonNull String tag) {
        return (priority.getIntValue() < minPriority.getIntValue() || !TextUtils.equals(tag,this.mTag));
    }
}
