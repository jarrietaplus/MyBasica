package com.nahuelsoftware.mybasica

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.view.get
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

/*
    var arrayAdapter: ArrayAdapter<String>? = TODO()
    var stringArrayList: ArrayList<String> = TODO()*/
    lateinit var myAdapter: BluetoothAdapter
    var blueReceiver: MyReceiver = MyReceiver()
    var intentFilter: IntentFilter = IntentFilter()


class Activity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)

        val scanButton: Button = this.findViewById(R.id.scanButton)
        val scanListView: ListView = this.findViewById(R.id.scannedListView)
        myAdapter = BluetoothAdapter.getDefaultAdapter()

//        val textVoltage: TextView = this.findViewById(R.id.twBattVoltage)
//        val textTechnology: TextView = this.findViewById(R.id.twBattTechnology)
//        val textTemperature: TextView = this.findViewById(R.id.twBattTemperature)
//        val textLevel: TextView = this.findViewById(R.id.twBattLevel)

//ListView
        val arrayAdapter:ArrayAdapter<*>
        val parametros = mutableListOf<String>("Tecnología", "Voltage","Temperatura","Nivel de carga", "Status")
        val listVwValores: ListView = this.findViewById(R.id.scannedListView)
        //presento valores
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,parametros)
        listVwValores.adapter= arrayAdapter
        //atiendo pulsación
        listVwValores.setOnItemClickListener() { parent, view, position, id ->
            Toast.makeText(this,parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
        }
//fin ListView

        scanButton.setOnClickListener {
            myAdapter.startDiscovery()
//            Toast.makeText(this, "Empiezo a scanear", Toast.LENGTH_SHORT).show()
//            textVoltage.text = "Voltage: ${blueReceiver.getBatteryVoltage().toString()}Volts"
//            textTechnology.text = "Tenología: ${blueReceiver.getBatteryTechnology()}"
//            textLevel.text = "Nivel de carga: ${blueReceiver.getBatteryLevel().toString()}%"
//            textTemperature.text = "Temperatura: ${blueReceiver.getBatteryTemperature().toString()}ºC"

            parametros[0] = "Tenología: ${blueReceiver.getBatteryTechnology()}"
            parametros[1] = "Voltage: ${blueReceiver.getBatteryVoltage().toString()}Volts"
            parametros[2] = "Temperatura: ${blueReceiver.getBatteryTemperature().toString()}ºC"
            parametros[3] = "Nivel de carga: ${blueReceiver.getBatteryLevel().toString()}%"
            parametros[4] = "Estado: ${blueReceiver.getBatteryStatus()}"

            listVwValores.adapter= arrayAdapter

        }

    }

    override fun onStart() {
        super.onStart()

//        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(blueReceiver, intentFilter)

    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(blueReceiver)

    }

    /*
       class MyBroadcastReceiver : BroadcastReceiver() {
           val TAG = "MyBroadcastReceiver"
           override fun onReceive(context: Context, intent: Intent) {
               var action:String = intent.getAction().toString()
               if(BluetoothDevice.ACTION_FOUND.equals(action)){
                   Toast.makeText(context, "Aqui pasa algo...", Toast.LENGTH_LONG).show()
               }else{
                   Toast.makeText(context, "Aqui no pasa nada", Toast.LENGTH_LONG).show()
               }

               StringBuilder().apply {
                   append("Action: ${intent.action}\n")
                   append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
                   toString().also { log ->
                       Log.d(TAG, log)
                       Toast.makeText(context, log, Toast.LENGTH_LONG).show()
                   }
               }
           }
       }

   }

   private val blueReceiver: BroadcastReceiver = object : BroadcastReceiver() {
       override fun onReceive(context: Context, intent: Intent) {
           var action:String = intent.getAction().toString()
           if(BluetoothDevice.ACTION_FOUND.equals(action)){
               Toast.makeText(context, "Aqui pasa algo...", Toast.LENGTH_LONG).show()
           }else{
               Toast.makeText(context, "Aqui no pasa nada", Toast.LENGTH_LONG).show()
           }
       }*/

}
