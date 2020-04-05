package com.knewbie.news.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class NetworkChange extends BroadcastReceiver {

    public final int wifi = 2, mobile = 1, none = 0;
    public int oldState = none;
    public List<OnNetWorkChange> onNetWorkChanges = new ArrayList<>();
    private static NetworkChange networkChange;

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    public interface OnNetWorkChange {
        void onChange(int wifi, int mobile, int none, int oldState, int newStatus);
    }

    public static NetworkChange getInstance() {
        if (networkChange == null ) {
            networkChange = new NetworkChange();
        }
        return networkChange;
    }

    public List<OnNetWorkChange> getOnNetWorkChanges() {
        return onNetWorkChanges;
    }

    public void setOnNetWorkChanges(List<OnNetWorkChange> onNetWorkChanges) {
        this.onNetWorkChanges = onNetWorkChanges;
    }
/*public List<onNetWorkChange> void setOnNetWorkChanges(onNetWorkChange onNetWorkChanges) {}
    @Override
    public void onReceive(Context context, Intent intent) {

    }*/
}
