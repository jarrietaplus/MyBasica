package com.nahuelsoftware

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import java.util.UUID

class BluetoothMio {


    //    class BluetoothJhr {
    var bluetoothAdapter: BluetoothAdapter? = null
    var context: android.content.Context? = null
    var Lista: java.util.ArrayList<String> = java.util.ArrayList<Any?>()
    var ListaDispositivos: android.widget.ListView? = null
    var EntradaAnterior = false
    private var SocketBlue: BluetoothSocket? = null
    var ClaseAnterior: java.lang.Class<*>? = null
    var MensajeAcumulativo: String? = null
    var CargaMensaje: String? = null
    var MensajeNormal: String? = null
    var MensajeElimina: String? = null
    private var MConexionHilo: ConnectedThread? = null
    var BAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    var context2: android.content.Context? = null

    constructor(contextActivity: android.content.Context?, listView: android.widget.ListView?) {
        context = contextActivity
        ListaDispositivos = listView
    }

    fun EncenderBluetooth(): android.widget.ListView? {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            Toast.makeText(context, "Tu Dispositivo No Soporta Bluetooth", 1).show()
        } else if (!bluetoothAdapter.isEnabled()) {
            val OnBluetooth = Intent("android.bluetooth.adapter.action.REQUEST_ENABLE")
            context.startActivity(OnBluetooth)
            while (!bluetoothAdapter.isEnabled()) {
                Sleep()
            }
            val Dispositivos: Set<BluetoothDevice> = bluetoothAdapter.getBondedDevices()
            if (Dispositivos.size > 0) {
                val var3: Iterator<*> = Dispositivos.iterator()
                while (var3.hasNext()) {
                    val Dispo: BluetoothDevice = var3.next() as BluetoothDevice
                    Lista.add(Dispo.getName() + "\n" + Dispo.getAddress())
                    val adapter = ArrayAdapter(context, 17367043, Lista)
                    ListaDispositivos.setAdapter(adapter)
                }
            }
        } else if (bluetoothAdapter.isEnabled()) {
            val Dispositivos: Set<BluetoothDevice> = bluetoothAdapter.getBondedDevices()
            if (Dispositivos.size > 0) {
                val var7: Iterator<*> = Dispositivos.iterator()
                while (var7.hasNext()) {
                    val Dispo: BluetoothDevice = var7.next() as BluetoothDevice
                    Lista.add(Dispo.getName() + "\n" + Dispo.getAddress())
                    val adapter = ArrayAdapter(context, 17367043, Lista)
                    ListaDispositivos.setAdapter(adapter)
                }
            }
        }
        return ListaDispositivos
    }

    fun Disp_Seleccionado(
        view: android.view.View,
        Posicion: Int,
        SegundaActivity: java.lang.Class<*>?
    ) {
        if (EntradaAnterior) {
            try {
                SocketBlue.close()
            } catch (var6: java.io.IOException) {
                var6.printStackTrace()
            }
        }
        val Imformacion: String = (view as TextView).getText().toString()
        Direccion = Imformacion.substring(Imformacion.length - 17)
        val Siguente = Intent(context, SegundaActivity)
        context.startActivity(Siguente)
    }

    fun DireccionMac(): String {
        return Direccion
    }

    private fun Sleep() {
        try {
            java.lang.Thread.sleep(1000L)
        } catch (var2: java.lang.InterruptedException) {
            var2.printStackTrace()
        }
    }

    constructor(ClaseAnterior1: java.lang.Class<*>, contextClase: android.content.Context?) {
        context2 = contextClase
        EstadoBluetooth()
        ClaseAnterior = ClaseAnterior1
    }

    fun EstadoBluetooth() {
        if (BAdapter == null) {
            Toast.makeText(context2, "El Dispositivo No Soporta Bluetooth", 1).show()
        } else if (!BAdapter.isEnabled()) {
            val i = Intent("android.bluetooth.adapter.action.REQUEST_ENABLE")
            context2.startActivity(i)
        }
    }

    fun ConectaBluetooth() {
        val Dispositivo: BluetoothDevice = BAdapter.getRemoteDevice(Direccion)
        try {
            SocketBlue = CreacionBlueSocket(Dispositivo)
        } catch (var6: java.io.IOException) {
            Toast.makeText(context2, "La Creacion Del Socket Fallo", 1).show()
        }
        try {
            SocketBlue.connect()
        } catch (var5: java.io.IOException) {
            try {
                SocketBlue.close()
            } catch (var4: java.io.IOException) {
            }
        }
        MConexionHilo = ConnectedThread(SocketBlue)
        MConexionHilo.start()
        MConexionHilo!!.Tx("j")
        EntradaAnterior = true
    }

    fun CierraConexion() {
        val i = Intent(context2, ClaseAnterior)
        try {
            SocketBlue.close()
        } catch (var3: java.io.IOException) {
        }
        context2.startActivity(i)
    }

    @kotlin.Throws(java.io.IOException::class)
    private fun CreacionBlueSocket(device: BluetoothDevice): BluetoothSocket {
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID)
    }

    fun DireccionToast() {
        Toast.makeText(context2, Direccion + "", 1).show()
    }

    fun Rx(): String? {
        return MensajeAcumulativo
    }

    fun ResetearRx() {
        MensajeAcumulativo = ""
    }

    fun Tx(MensajeE: String) {
        MConexionHilo!!.Tx(MensajeE)
    }

    private inner class ConnectedThread(Socket: BluetoothSocket) : java.lang.Thread() {
        private val MeInStream: java.io.InputStream?
        private val MsOuStream: java.io.OutputStream?
        override fun run() {
            val MemoriaTemporal = ByteArray(256)
            while (true) {
                try {
                    val bytes: Int = MeInStream.read(MemoriaTemporal)
                    CargaMensaje = String(MemoriaTemporal, 0, bytes)
                    MensajeAcumulativo = MensajeAcumulativo + CargaMensaje
                    MensajeElimina = MensajeElimina + CargaMensaje
                } catch (var4: java.io.IOException) {
                    return
                }
            }
        }

        fun Tx(Entrada: String) {
            val MensajeBuffer: ByteArray = Entrada.toByteArray()
            try {
                MsOuStream.write(MensajeBuffer)
            } catch (var10: java.io.IOException) {
                Toast.makeText(context2, "La Conexion Fallo Vuelve A Conectar", 1).show()
                var Finaliza: Intent? = null
                Finaliza = Intent(context2, ClaseAnterior)
                context2.startActivity(Finaliza)
                try {
                    SocketBlue.close()
                } catch (var9: java.io.IOException) {
                    try {
                        SocketBlue.close()
                    } catch (var8: java.io.IOException) {
                    }
                }
            }
        }

        init {
            var DatosIn: java.io.InputStream? = null
            var DatosOut: java.io.OutputStream? = null
            try {
                DatosIn = Socket.getInputStream()
                DatosOut = Socket.getOutputStream()
            } catch (var6: java.io.IOException) {
            }
            MeInStream = DatosIn
            MsOuStream = DatosOut
        }
    }

    companion object {
        var Direccion = ""
        private val BTMODULEUUID: java.util.UUID =
            java.util.UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    }
//    }
}

