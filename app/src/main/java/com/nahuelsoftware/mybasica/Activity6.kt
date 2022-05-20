package com.nahuelsoftware.mybasica

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.util.*


class Activity6(digits: Int) : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_6)

//        lineChart = findViewById(R.id.lineChart)
//        setLineCharData()
    }
}

    fun setLineCharData(){
        lateinit var lineChart: LineChart
/*
        val xvalue = ArrayList<String>()
        xvalue.add("11.00 AM")
        xvalue.add("12.00 AM")
        xvalue.add("1.00 PM")
        xvalue.add("3.00 PM")
        xvalue.add("7.00 PM")
*/
//pares de valores (Entry)
        val lineentry1 = ArrayList<Entry>()
        lineentry1.add(Entry(0f, 20f))
        lineentry1.add(Entry(1f, 64.9f))
        lineentry1.add(Entry(2f, 2f))
        lineentry1.add(Entry(3f, 10f))
        lineentry1.add(Entry(4f, 28f))
        val lineentry2 = ArrayList<Entry>()
        lineentry2.add(Entry(0f, 50f))
        lineentry2.add(Entry(1f, 20f))
        lineentry2.add(Entry(2f, 10f))
        lineentry2.add(Entry(3f, 70f))
        lineentry2.add(Entry(4f, 38f))
//aspecto
        val linedataset1 = LineDataSet(lineentry1, "First Chart")
//        linedataset1.color = resources.getColor(R.color.teal_700)
        linedataset1.lineWidth = 2f
        val linedataset2 = LineDataSet(lineentry2, "Second Chart")
//        linedataset2.color = resources.getColor(R.color.purple_700)
        linedataset2.lineWidth = 2f

//        lineChart.legend.isEnabled = false //quita "leyendas"
        lineChart.axisRight.isEnabled = false //quita el eje Y-derecho
//        lineChart.description.isEnabled = false // quita descripcion
        lineChart.setScaleEnabled(false) // no deja escalar el grafico

        val xAxis: XAxis = lineChart.xAxis
        xAxis.granularity= 1f //paso de cuadricula vertical y minimo cuando "escalable"
// fin aspecto
        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(linedataset1)
        dataSets.add(linedataset2)
        val data = LineData(dataSets)
        lineChart.data = data
        lineChart.invalidate()

//        lineChart.setBackgroundColor(resources.getColor(android.R.color.holo_orange_light))
        lineChart.animateXY(3000,3000)
    }

