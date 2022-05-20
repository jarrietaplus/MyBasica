package com.nahuelsoftware.mybasica

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.Format
import java.text.SimpleDateFormat
import java.util.Date

class MyReceiver : BroadcastReceiver() {
    private var batteryVoltage:Float = 0F
    private var batteryTechnology =""
    private var batteryTemperature = 0
    private var batteryLevel = 0
    private var batteryStatus = ""


    override fun onReceive(context: Context, intent: Intent) {

        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        var action:String = intent.getAction().toString()
        System.out.println("ON RECEIVE GRAL: $action")

        when(action) {
            "android.intent.action.TIME_TICK" -> {
                System.out.println("ON RECEIVE: $action")
                System.out.println ("STATUS : ${intent.getBooleanExtra("state", false)}")
                val msgStr = StringBuilder("Hora actual: ")
                val formatter: Format = SimpleDateFormat("hh:mm:ss a")
                msgStr.append(formatter.format(Date()))
                System.out.println ("${msgStr}")
                Toast . makeText (context, msgStr, Toast.LENGTH_SHORT).show()
                }
            "android.intent.action.BATTERY_CHANGED" -> {
                System.out.println("ON RECEIVE: $action")
                batteryVoltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,-1).toFloat() / 1000
                System.out.println("BATTERY VOLTAGE : $batteryVoltage Volts")
                batteryTemperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10
                System.out.println("BATTERY TEMPERATURE : ${batteryTemperature}ºC")
                batteryTechnology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY).toString()
                System.out.println("BATTERY TECHNOLOGY : $batteryTechnology")
                batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                System.out.println("BATTERY LEVEL : $batteryLevel%")

                var status:Int = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                    when(status){
                        BatteryManager.BATTERY_STATUS_FULL -> batteryStatus="FULL"
                        BatteryManager.BATTERY_STATUS_UNKNOWN -> batteryStatus="UNKNOWN"
                        BatteryManager.BATTERY_STATUS_CHARGING -> batteryStatus="CHARGING"
                        BatteryManager.BATTERY_STATUS_DISCHARGING -> batteryStatus="DISCHARGING"
                        BatteryManager.BATTERY_STATUS_NOT_CHARGING -> batteryStatus="NOT_CHARGING"
                    }
                System.out.println("Battery Status: $batteryStatus")
                }
            }

/*        if(action == "android.intent.action.TIME_TICK"){
            System.out.println("ON RECEIVE: $action")
            System.out.println("STATUS : ${intent.getBooleanExtra("state",false)} - ${nro++}")

            val msgStr = StringBuilder("Hora actual: ")
            val formatter: Format = SimpleDateFormat("hh:mm:ss a")
            msgStr.append(formatter.format(Date()))
            System.out.println("${msgStr}")
            Toast.makeText(context, msgStr, Toast.LENGTH_SHORT).show()
         } else if (action == "android.intent.action.BATTERY_CHANGED"){
            System.out.println("ON RECEIVE: $action")
            batteryVoltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,-1).toFloat() / 1000
            System.out.println("BATTERY VOLTAGE : $batteryVoltage Volts")
            batteryTemperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10
            System.out.println("BATTERY TEMPERATURE : ${batteryTemperature}ºC")
            batteryTechnology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY).toString()
            System.out.println("BATTERY TECHNOLOGY : $batteryTechnology")
            batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            System.out.println("BATTERY LEVEL : $batteryLevel%")
        }*/
        /*
        if(BluetoothDevice.ACTION_FOUND.equals(action)){
            Toast.makeText(context, "Aqui pasa algo...", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context, "Aqui no pasa nada", Toast.LENGTH_LONG).show()
        }*/
    }
//externalizo datos bajo demanda

    fun getBatteryVoltage(): Float {
        return batteryVoltage
    }
    fun getBatteryTechnology(): String {
        return batteryTechnology
    }
    fun getBatteryTemperature(): Int {
        return batteryTemperature
    }
    fun getBatteryLevel(): Int {
        return batteryLevel
    }
    fun getBatteryStatus(): String {
        return batteryStatus
    }
}