package com.nahuelsoftware.mybasica

import android.bluetooth.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.wifi.SupplicantState
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.nahuelsoftware.mybasica.Activity2.Companion.PERMISSION_CODE_ACCEPTED
import java.util.*


class Activity2 : AppCompatActivity() {

    companion object {
        const val PERMISSION_CODE_ACCEPTED = 1
        const val PERMISSION_CODE_NOT_AVAILABLE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        var texto:TextView = this.findViewById(R.id.textView)
        val wifiManager = this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val botonSwitch:Button = this.findViewById(R.id.buttonSwitch)
        botonSwitch.setBackgroundColor(Color.parseColor("#FF7CB342"))
        val botonUpdate:Button = this.findViewById(R.id.buttonUpdate)
        botonUpdate.setBackgroundColor(Color.parseColor("#FF7CB342"))

        requestLocationPermission() //solicito permiso para acceder datos wifi,permiso que si se da o se deniega ya queda asi

/*        when(requestLocationPermission()){
            Activity2.PERMISSION_CODE_ACCEPTED -> getWifiSSID()
        }*/
/*
        if(wifiManager.isWifiEnabled){
            botonSwitch.text= "Desconectar"
            texto.setText("WiFi conectado")
        }
        else if (!wifiManager.isWifiEnabled){
            botonSwitch.text= "Conectar"
            texto.setText("WiFi desconectado")
        }*/
// Op 1
//        var ipAddress:Int = wifiManager.connectionInfo.getIpAddress()
// Op 2 perfecto
        var wifiInfo = wifiManager.getConnectionInfo()
        var ipAddress2:Int = wifiInfo.ipAddress
        var ipAddress = android.text.format.Formatter.formatIpAddress(ipAddress2)

//        Toast.makeText(this,"IP Address : $ipAddress",Toast.LENGTH_LONG).show()
        System.out.println("IP Address : $ipAddress")
//ListView
        var arrayAdapter:ArrayAdapter<*>
        var parametros = mutableListOf<String>("IP Address : $ipAddress", "WiFi Connected: ${wifiManager.isWifiEnabled}","Signal level: ${wifiInfo.rssi} dBm","SSID: ${wifiInfo.ssid}", "BSSID: ${wifiInfo.bssid}", "Speed: ${wifiInfo.linkSpeed} Mbps", "NetID: ${wifiInfo.networkId}", "Channel freq: ${wifiInfo.frequency} MHz")
        val listVwValores: ListView = this.findViewById(R.id.lista_dispositivos)
        //presento valores
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,parametros)
        listVwValores.adapter= arrayAdapter
        //atiendo pulsación
        listVwValores.setOnItemClickListener() { parent, view, position, id ->
            Toast.makeText(this,parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
        }
//fin ListView
/*
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val panelIntent = Intent(android.provider.Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                    startActivityForResult(panelIntent, 0)
                } else {
                    wifiManager.isWifiEnabled = true  //con esto conecta , con false desconecta...
                }
*/
// Atencion boton Conectar/Desconectar WiFi
        botonSwitch.setOnClickListener(){
            if(wifiManager.isWifiEnabled){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val panelIntent = Intent(android.provider.Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                    startActivityForResult(panelIntent, 0)
                } else {
                    wifiManager.isWifiEnabled = false  //con esto desconecta...
                }
//                texto.setText("WiFi desconectado")
//                System.out.println("wifi status desc: ${wifiManager.isWifiEnabled}")
            }
            else if (!wifiManager.isWifiEnabled){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val panelIntent = Intent(android.provider.Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                    startActivityForResult(panelIntent, 0)
                } else {
                    wifiManager.isWifiEnabled = true  //con esto conecta ...
                }
//                boton.text= "Desconectar"
//                texto.setText("WiFi conectado")
//                System.out.println("wifi status conn: ${wifiManager.isWifiEnabled}")
            }
            botonUpdate.setBackgroundColor(Color.parseColor("#FFF4511E"))
        }

        botonUpdate.setOnClickListener(){
            wifiInfo = wifiManager.getConnectionInfo()
            ipAddress2 = wifiInfo.ipAddress
            ipAddress = android.text.format.Formatter.formatIpAddress(ipAddress2)

            parametros = mutableListOf<String>("IP Address : $ipAddress", "WiFi Connected: ${wifiManager.isWifiEnabled}","Signal level: ${wifiInfo.rssi} dBm","SSID: ${wifiInfo.ssid}", "BSSID: ${wifiInfo.bssid}", "Speed: ${wifiInfo.linkSpeed} Mbps", "NetID: ${wifiInfo.networkId}", "Channel freq: ${wifiInfo.frequency} MHz")
            arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,parametros)
            listVwValores.adapter= arrayAdapter
            botonUpdate.setBackgroundColor(Color.parseColor("#FF7CB342"))

            System.out.println("BSSID: ${wifiInfo.bssid} - RSSi: ${wifiInfo.rssi} dBm - NetID: ${wifiInfo.networkId} - SSID: ${wifiInfo.ssid} - Speed: ${wifiInfo.linkSpeed} Mbps - Channel freq: ${wifiInfo.frequency}")
        }
    }
// funcion que solicita el permiso a usuario para acceder a los datos wifi
//si se responde si para siempre, no vuelve a preguntar y accede siempre
//si se responde no para siempre no vuelve a preguntar y no accede nunca
//si se responde solo esta vez, lo vovera a preguntar cada vez que entremos
    fun requestLocationPermission(): Int {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                // request permission
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    Activity2.PERMISSION_CODE_ACCEPTED)
            }
        } else {
            // already granted
            return Activity2.PERMISSION_CODE_ACCEPTED
        }

        // not available
        return Activity2.PERMISSION_CODE_NOT_AVAILABLE
    }
// esta funcion no se usa es solo otro ejemplo de como reclamar el ssid (aplicable tambien a otros datos wifi)
    fun getWifiSSID() {
        val mWifiManager: WifiManager = (this.getApplicationContext().getSystemService(Context.WIFI_SERVICE) as WifiManager)!!
        val info: WifiInfo = mWifiManager.getConnectionInfo()

        if (info.getSupplicantState() === SupplicantState.COMPLETED) {
            val ssid: String = info.getSSID()
            System.out.println("wifi name: ${ssid}")
        } else {
            System.out.println("could not obtain the wifi name")
        }
    }
}