package com.example.myapplication.broadcast_example

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneModeEnabled = intent?.getBooleanExtra("state", false)
    if(isAirplaneModeEnabled != null)
        {
            if(isAirplaneModeEnabled)
            {
                Toast.makeText(context,"AIRplane mode enabled",Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(context, "Airplane Mode Disabled", Toast.LENGTH_LONG).show()
            }

        }
    }
}