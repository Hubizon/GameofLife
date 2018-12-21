package com.example.hubert.gameoflife;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;

class SampleLifecycleListener implements LifecycleObserver {

    private OnLifeCycleEventChange mListener;
    public interface OnLifeCycleEventChange {
        void stopGlobalTimer();
        void startGlobalTimer();
    }

    SampleLifecycleListener(Context context) {
        if (context instanceof OnLifeCycleEventChange) {
            mListener = (OnLifeCycleEventChange) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onMoveToForeground() {
        Log.d("SampleLifecycle", "Returning to foreground…");
        mListener.startGlobalTimer();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onMoveToBackground() {
        Log.d("SampleLifecycle", "Moving to background…");
        mListener.stopGlobalTimer();
    }
}