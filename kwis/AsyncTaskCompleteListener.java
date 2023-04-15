package com.hfad.kwis;

/**
 * Created by Marc on 24-8-2017.
 */

public interface AsyncTaskCompleteListener<T> {
    void onTaskComplete(T result);
}
