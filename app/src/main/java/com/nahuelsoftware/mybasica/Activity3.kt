package com.nahuelsoftware.mybasica

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Activity3 : AppCompatActivity() {

    private val REQUEST_CODE_ENABLE_BT:Int = 1
    private val REQUEST_CODE_DISCOVERABLE_BT = 2
    private val DISCOVERABLE_DURATION = 222

    //BT adapter
    lateinit var bAdapter: BluetoothAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        val bluetoothIv:ImageView = this.findViewById(R.id.bluetoothIv)
        val bluetoothStatusTv: TextView = this.findViewById(R.id.bluetoothStatusTv)
        val turnOnBtn:Button = this.findViewById(R.id.turnOnBtn)
        val turnOffBtn:Button = this.findViewById(R.id.turnOffBtn)
        val discoverableBtn:Button = this.findViewById(R.id.disciverableBtn)
        val pairedBtn:Button = this.findViewById(R.id.pairedBtn)
        val pairedTv:TextView = this.findViewById(R.id.pairedTv)


        //inicializo BT adapter
        bAdapter = BluetoothAdapter.getDefaultAdapter()
        //veo si BT está disponible o no
        if (bAdapter == null) {
            bluetoothStatusTv.text = "BT no disponible en este equipo"
        } else {
            bluetoothStatusTv.text = "BT disponible en este equipo"
        }
        //indico estado de BT (on/off) en imagen
        if(bAdapter.isEnabled){
            //BT on
            bluetoothIv.setImageResource(R.drawable.ic_bluetooth_on)
        }
        else{
            //BT off
            bluetoothIv.setImageResource(R.drawable.ic_bluetooth_off)
        }
        //poner BT ON
        turnOnBtn.setOnClickListener {
            if(bAdapter.isEnabled) {
                //ya esta ON
                Toast.makeText(this,"BT ya está ON", Toast.LENGTH_SHORT).show()
            }else{
                //se pide activacion de BT
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent, REQUEST_CODE_ENABLE_BT)
            }
        }
        //poner BT OFF
        turnOffBtn.setOnClickListener {
            if(!bAdapter.isEnabled) {
                //ya esta ON
                Toast.makeText(this,"BT ya está OFF", Toast.LENGTH_SHORT).show()
            }else{
                //se desactiva BT
                bAdapter.disable()
                bluetoothIv.setImageResource(R.drawable.ic_bluetooth_off)
                Toast.makeText(this,"BT OFF", Toast.LENGTH_SHORT).show()
            }
        }
        //poner BT VISIBLE
        discoverableBtn.setOnClickListener {
            if(!bAdapter.isDiscovering){
                Toast.makeText(this,"Haciendo visible el equipo...", Toast.LENGTH_SHORT).show()
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
                intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,DISCOVERABLE_DURATION)
                startActivityForResult(intent, REQUEST_CODE_DISCOVERABLE_BT)
            }else{
            //nada
            }
        }
        //obtener lista de pareados
        pairedBtn.setOnClickListener {
            if(bAdapter.isEnabled) {
                pairedTv.text = "Equipos detectados"
                //obtener la lista
                val devices = bAdapter.bondedDevices
                for(device in devices){
                    val deviceName = device.name
                    val deviceAddress = device.address
                    pairedTv.append("\nEquipo: $deviceName, $deviceAddress")
                }
            }else{
                Toast.makeText(this,"Poner BT ON para acceder a esta funcion", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val bluetoothIv:ImageView = this.findViewById(R.id.bluetoothIv)
        val bluetoothStatusTv: TextView = this.findViewById(R.id.bluetoothStatusTv)
//        System.out.println("requestCode : $requestCode resultCode: $resultCode")

        when(requestCode) {
            REQUEST_CODE_ENABLE_BT ->
                if(resultCode == Activity.RESULT_OK){
                    bluetoothIv.setImageResource(R.drawable.ic_bluetooth_on)
                    Toast.makeText(this,"BT ON", Toast.LENGTH_SHORT).show()
                }else{
                    //el usuario no deja activar el BT
                    Toast.makeText(this,"BT no se pudo activar", Toast.LENGTH_SHORT).show()
                }
            REQUEST_CODE_DISCOVERABLE_BT ->
                if(resultCode == DISCOVERABLE_DURATION){
                    bluetoothIv.setImageResource(R.drawable.ic_bluetooth_on)
                    Toast.makeText(this,"El quipo es visible", Toast.LENGTH_SHORT).show()
                }else{
                    //el usuario no deja activar el BT
                    Toast.makeText(this,"Visibilidad no se pudo activar", Toast.LENGTH_SHORT).show()
                }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
