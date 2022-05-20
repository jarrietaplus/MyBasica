package com.nahuelsoftware.mybasica

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

var BASE_URL = ""
//    private val BASE_URL = "http://www.nahuel.com/help/cuore/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn1: Button = findViewById(R.id.button1)
        var btn2: Button = findViewById(R.id.button2)
        var btn3: Button = findViewById(R.id.button3)
        var btn4: Button = findViewById(R.id.button4)
        var btn5: Button = findViewById(R.id.button5)
        var btn6: Button = findViewById(R.id.button6)
        var btn7: Button = findViewById(R.id.button7)
        var btn8: Button = findViewById(R.id.button8)
        var btn9: Button = findViewById(R.id.button9)
        var btn10:Button = findViewById(R.id.button10)
        var btn11:Button = findViewById(R.id.button11)
        var btn12:Button = findViewById(R.id.button12)

        btn1.setOnClickListener {
            val intent = Intent(this, Activity1::class.java)
            startActivity(intent)
        }
        btn2.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }
        btn3.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }
        btn4.setOnClickListener {
            val intent = Intent(this, Activity4::class.java)
            startActivity(intent)
        }
        btn5.setOnClickListener {
            val intent = Intent(this, Activity5::class.java)
            startActivity(intent)
        }
        btn6.setOnClickListener {
            val intent = Intent(this, Activity61::class.java)
            startActivity(intent)
        }
        btn7.setOnClickListener {
            val intent = Intent(this, Activity7::class.java)
            startActivity(intent)
        }
        btn8.setOnClickListener {
            val intent = Intent(this, Activity8::class.java)
            startActivity(intent)
        }
        btn9.setOnClickListener {
            Toast.makeText(this,"Boton 9 pulsado",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.accion1 -> Toast.makeText(this,"accion1",Toast.LENGTH_SHORT).show()
            R.id.accion2 -> Toast.makeText(this,"accion2",Toast.LENGTH_SHORT).show()
            R.id.accion3 -> Toast.makeText(this,"accion3",Toast.LENGTH_SHORT).show()
            R.id.accion4 -> {
                cerrarAplicacion()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cerrarAplicacion() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Atención")
        dialogBuilder.setMessage("¿Quieres abandonar la aplicación?")
        /*cuando me dan OK....*/
        dialogBuilder.setPositiveButton(getString(android.R.string.ok)) { dialog, _ ->
            finishAffinity()
//            this.finishAndRemoveTask()
            dialog.dismiss()
        }
        /*cuando me dan NOK....*/
        dialogBuilder.setNegativeButton(getString(android.R.string.no)) { dialog, _ ->
            dialog.dismiss()
        }
        dialogBuilder.show()
    }

}