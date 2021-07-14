package com.devmantosh.callnumber;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.TelephonyManager;

import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

import java.util.List;

@CapacitorPlugin(
        permissions = {
                @Permission(
                        alias = "call",
                        strings = {Manifest.permission.CALL_PHONE}
                )
        }
)
public class CallNumber extends Plugin {

    PluginCall call;

    @PluginMethod()
    public void showFilePicker(PluginCall call) {
        this.call = call;
        if (getPermissionState("call") != PermissionState.GRANTED) {
            requestPermissionForAlias("call", call, "callPermsCallback");
        } else {
            callNumber(this.call);
        }
    }

    @PermissionCallback
    private void callPermsCallback(PluginCall call) {
        if (getPermissionState("call") == PermissionState.GRANTED) {
            callNumber(this.call);
        } else {
            call.reject("Permission is required to do call");
        }
    }

    private void callNumber(PluginCall call) {
        String numberToCall = call.getString("number", "").replaceAll("#", "%23");
        boolean bypassAppChooser = call.getBoolean("bypassAppChooser", false);
        if (!numberToCall.startsWith("tel:")) {
            numberToCall = String.format("tel:%s", numberToCall);
        }
        try {
            boolean enableTelephony = isTelephonyEnabled();
            Intent intent = new Intent(enableTelephony ? (bypassAppChooser ? Intent.ACTION_DIAL : Intent.ACTION_CALL) : Intent.ACTION_VIEW);
            intent.setData(Uri.parse(numberToCall));

            if ((enableTelephony == false) && bypassAppChooser) {
                intent.setPackage(getDialerPackage(intent));
            }
            getContext().startActivity(intent);
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getLocalizedMessage(), null, e);
        }
    }

    private boolean isTelephonyEnabled() {
        TelephonyManager tm = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null && tm.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }

    private String getDialerPackage(Intent intent) {
        PackageManager packageManager = (PackageManager) getContext().getPackageManager();
        List activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).toString().toLowerCase().contains("com.android.server.telecom")) {
                return "com.android.server.telecom";
            }
            if (activities.get(i).toString().toLowerCase().contains("com.android.phone")) {
                return "com.android.phone";
            } else if (activities.get(i).toString().toLowerCase().contains("call")) {
                return activities.get(i).toString().split("[ ]")[1].split("[/]")[0];
            }
        }
        return "";
    }
}