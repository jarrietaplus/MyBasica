package com.nahuelsoftware.mybasica

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.util.*

class Activity61 : AppCompatActivity() {

    lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_61)

        pieChart = findViewById(R.id.pieChart)
        setUpPieChart()
        setPieCharData()

        setLineCharData()

        setBarCharData()

        setBarCharData1()
    }
//BarChart de GOLF
    fun setBarCharData1(){
        var barChart: BarChart
        barChart = findViewById(R.id.barChart1)

    //pares de valores (BarEntry) berdies
    val barentry1 = ArrayList<BarEntry>()
    barentry1.add(BarEntry(5f, 3f))
    barentry1.add(BarEntry(15f, 3f))

    //pares de valores (BarEntry) pares
    val barentry2 = ArrayList<BarEntry>()
    barentry2.add(BarEntry(1f, 2f))
    barentry2.add(BarEntry(4f, 2f))
    barentry2.add(BarEntry(12f, 2f))
    barentry2.add(BarEntry(16f, 2f))
    barentry2.add(BarEntry(17f, 2f))

    //pares de valores (BarEntry) boggies
    val barentry3 = ArrayList<BarEntry>()
    barentry3.add(BarEntry(2f, 1f))
    barentry3.add(BarEntry(6f, 1f))
    barentry3.add(BarEntry(10f, 1f))
    barentry3.add(BarEntry(13f, 1f))
    barentry3.add(BarEntry(18f, 1f))

    //pares de valores (BarEntry) boggies
    val barentry4 = ArrayList<BarEntry>()
    barentry4.add(BarEntry(9f, 0.2f))
    barentry4.add(BarEntry(11f, 0.2f))

    //pares de valores (BarEntry) peor
    val barentry5 = ArrayList<BarEntry>()
    barentry5.add(BarEntry(3f, -2f))
    barentry5.add(BarEntry(7f, -1f))
    barentry5.add(BarEntry(8f, -2f))
    barentry5.add(BarEntry(14f, -1f))

    val bardataset1 = BarDataSet(barentry1, "birdie")
    bardataset1.setColor(Color.parseColor("#33cc00"))
    val bardataset2 = BarDataSet(barentry2, "par")
    bardataset2.setColor(Color.parseColor("#33ff00"))
    val bardataset3 = BarDataSet(barentry3, "bogey")
    bardataset3.setColor(Color.parseColor("#ffff00"))
    val bardataset4 = BarDataSet(barentry4, "2bogey")
    bardataset4.setColor(Color.parseColor("#000000"))
    val bardataset5 = BarDataSet(barentry5, "peor")
    bardataset5.setColor(Color.parseColor("#ff3300"))

    val data = BarData(bardataset1)
    data.addDataSet(bardataset2)
    data.addDataSet(bardataset3)
    data.addDataSet(bardataset4)
    data.addDataSet(bardataset5)
    data.setValueTextSize(0f)

//no hay descripcion
    val description: Description = barChart.getDescription()
    description.setEnabled(false) //con "true" aparece
//no hay eje X ?
    val xVals = ArrayList<String>()
    xVals.add("")
    xVals.add("")
    xVals.add("")
    xVals.add("3")
    xVals.add("")
    xVals.add("")
    xVals.add("6")
    xVals.add("")
    xVals.add("")
    xVals.add("9")
    xVals.add("")
    xVals.add("")
    xVals.add("12")
    xVals.add("")
    xVals.add("")
    xVals.add("15")
    xVals.add("")
    xVals.add("")

    val xAxis: XAxis = barChart.xAxis
    xAxis.isEnabled = true //false
    xAxis.setValueFormatter(IndexAxisValueFormatter(xVals)) //para incluir valores en eje X

    barChart.data = data
    barChart.invalidate()

    barChart.setBackgroundColor(Color.parseColor("#cccc66"))
    barChart.axisRight.isEnabled = false //quita el eje Y-derecho
    barChart.getAxisLeft().setEnabled(false)
    barChart.animateXY(0,1500)
    barChart.setScaleEnabled(false) //deja o no escalar el grafico
    barChart.setPinchZoom(false) //deja o no arrastrar con el dedo el graf (si escalable)


    }

//PieChart
    fun setPieCharData() {
        //pares de valores (PieEntry)
        val pieEntry1 = ArrayList<PieEntry>()
        pieEntry1.add(PieEntry(10f, "Parte 10"))
        pieEntry1.add(PieEntry(20f, "Parte 20"))
        pieEntry1.add(PieEntry(50f, "Parte 50"))
        pieEntry1.add(PieEntry(30f, "Parte 30"))
        pieEntry1.add(PieEntry(20f, "Parte 20"))

        val piedataset = PieDataSet(pieEntry1, "") // puedo poner un titulo en ""
        piedataset.setColors(Color.RED, Color.YELLOW, Color.CYAN, Color.GREEN, Color.GRAY)

        val data = PieData(piedataset)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)

        pieChart.setData(data)
        pieChart.invalidate()

    }
    fun setUpPieChart(){
        pieChart.setDrawHoleEnabled(true) //con titulo central o no
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelTextSize(12f)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.setCenterText("Titulo Central")
        pieChart.setCenterTextSize(24f)
        pieChart.getDescription().setEnabled(false) //descripcion si o no
        pieChart.setBackgroundColor(Color.LTGRAY)
        pieChart.animateXY(0,1500)


        var l: Legend = pieChart.getLegend()
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP)
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
        l.setOrientation(Legend.LegendOrientation.VERTICAL)
        l.setDrawInside(false)
        l.setEnabled(true)// leyenda si o no
    }
//BarChart
    fun setBarCharData(){
        var barChart: BarChart
        barChart = findViewById(R.id.barChart)

        //pares de valores (BarEntry)
        val barentry1 = ArrayList<BarEntry>()
        barentry1.add(BarEntry(0f, 20f))
        barentry1.add(BarEntry(1f, 40f))
        barentry1.add(BarEntry(2f, 30f))
        barentry1.add(BarEntry(3f, 10f))
        barentry1.add(BarEntry(4f, 60f))
        barentry1.add(BarEntry(5f, 50f))
        barentry1.add(BarEntry(6f, 30f))
        barentry1.add(BarEntry(7f, 10f))
        barentry1.add(BarEntry(8f, 45f))
        barentry1.add(BarEntry(9f, 55f))
        barentry1.add(BarEntry(10f, 0f))

        val barentry2 = ArrayList<BarEntry>()
        barentry2.add(BarEntry(0f, 10f))
        barentry2.add(BarEntry(1f, 50f))
        barentry2.add(BarEntry(2f, 30f))
        barentry2.add(BarEntry(3f, 20f))
        barentry2.add(BarEntry(4f, 40f))
        barentry2.add(BarEntry(5f, 20f))
        barentry2.add(BarEntry(6f, 0f))
        barentry2.add(BarEntry(7f, 30f))
        barentry2.add(BarEntry(8f, 25f))
        barentry2.add(BarEntry(9f, 35f))

        val bardataset1 = BarDataSet(barentry1, "First Value")
        bardataset1.setColor(Color.RED)

        val bardataset2 = BarDataSet(barentry2, "Second Value")
        bardataset2.setColor(Color.BLUE)

// fin aspecto
        val data = BarData(bardataset1)
        data.addDataSet(bardataset2) //sumo la segunda columna
//        data.setValueFormatter(LargeValueFormatter()) //no se que hace...
        data.setBarWidth(0.5f)
        data.groupBars(0.0f,0.0f,0.0f) //solo si hay mas de una columna
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM)
        barChart.axisRight.isEnabled = false //quita el eje Y-derecho

        val description: Description = barChart.getDescription()
        description.setEnabled(false) //con "true" aparece
        description.setText("Descripción del gráfico")

      //valores para eje X
        val xVals = ArrayList<String>()
        xVals.add("Jan")
        xVals.add("Feb")
        xVals.add("Mar")
        xVals.add("Apr")
        xVals.add("May")
        xVals.add("Jun")
        xVals.add("Jul")
        xVals.add("Aug")
        xVals.add("Sep")
        xVals.add("Oct")
        xVals.add("Nov")
        xVals.add("Dec")

        val xAxis: XAxis = barChart.xAxis
        xAxis.setTextSize(10f)
        xAxis.setTextColor(Color.BLUE)
        xAxis.setDrawAxisLine(true)
        xAxis.setDrawGridLines(true)
        xAxis.setLabelRotationAngle(-45f) //para rotar los valores del eje X
        xAxis.setValueFormatter(IndexAxisValueFormatter(xVals)) //para incluir valores en eje X
//        barChart.getXAxis().setCenterAxisLabels(true)

        barChart.data = data
        barChart.invalidate()

        barChart.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
        barChart.animateXY(2000,2000)

    }
//LineChart
    fun setLineCharData(){
        var lineChart: LineChart
        lineChart = findViewById(R.id.lineChart)

//pares de valores (Entry)
        val lineentry1 = ArrayList<Entry>()
        lineentry1.add(Entry(0f, 20f))
        lineentry1.add(Entry(1f, 64.9f))
        lineentry1.add(Entry(2f, 30f))
        lineentry1.add(Entry(3f, 10f))
        lineentry1.add(Entry(4f, 28f))
        lineentry1.add(Entry(5f, 0f))
        val lineentry2 = ArrayList<Entry>()
        lineentry2.add(Entry(0f, 50f))
        lineentry2.add(Entry(1f, 20f))
        lineentry2.add(Entry(2f, 10f))
        lineentry2.add(Entry(3f, 70f))
        lineentry2.add(Entry(5f, 38f))
        val lineentry3 = ArrayList<Entry>()
        lineentry3.add(Entry(0f, 60f))
//        lineentry3.add(Entry(1f, 45f))
//        lineentry3.add(Entry(2f, 45f))
//        lineentry3.add(Entry(3f, 45f))
//        lineentry3.add(Entry(4f, 45f))
        lineentry3.add(Entry(5f, 60f))
//aspecto
        val linedataset1 = LineDataSet(lineentry1, "First Chart")
        linedataset1.color = resources.getColor(R.color.teal_700)
        linedataset1.lineWidth = 2f
        linedataset1.setValueTextSize(10f)
        linedataset1.setCircleColor(Color.BLACK)
        linedataset1.setDrawCircleHole(false)
        linedataset1.setDrawFilled(true)
        linedataset1.setFormSize(10f) //tamaño cuadradito leyenda
        linedataset1.setDrawValues(false)
//        linedataset1.setFormLineWidth(10f)  //no se para que vale
//        linedataset1.enableDashedLine(10f,5f,0f)
//        linedataset1.enableDashedHighlightLine(10f, 5f, 0f) //no se para que vale

        val linedataset2 = LineDataSet(lineentry2, "Second Chart")
        linedataset2.color = resources.getColor(R.color.purple_700)
        linedataset2.lineWidth = 2f

        val linedataset3 = LineDataSet(lineentry3, "")
        linedataset3.setColor(Color.RED)
        linedataset3.lineWidth = 2f
        linedataset3.enableDashedLine(10f,5f,0f)
        linedataset3.setFormSize(0f)
        linedataset3.setDrawCircles(false)
        linedataset3.setDrawValues(false)
//        linedataset3.setValueTextSize(0f)
//        linedataset3.setCircleRadius(0f)

        lineChart.axisRight.isEnabled = false //quita el eje Y-derecho
        lineChart.setScaleEnabled(false) //deja o no escalar el grafico
        lineChart.setPinchZoom(false) //deja o no arrastrar con el dedo el graf (si escalable)
//        lineChart.legend.isEnabled = false //quita "leyendas"



        val xAxis: XAxis = lineChart.xAxis
        xAxis.granularity= 1f //paso de cuadricula vertical y minimo cuando "escalable"
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setTextSize(10f)
        xAxis.setTextColor(Color.RED)
        xAxis.setDrawAxisLine(true)
        xAxis.setDrawGridLines(true)
        xAxis.setLabelRotationAngle(0f)

        val description: Description = lineChart.getDescription()
        description.setEnabled(false) //con "true" aparece
        description.setText("Descripción del gráfico")

// fin aspecto
        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(linedataset1)
        dataSets.add(linedataset2)
        dataSets.add(linedataset3)
        val data = LineData(dataSets)
        lineChart.data = data
        lineChart.invalidate()

        lineChart.setBackgroundColor(resources.getColor(android.R.color.holo_orange_light))
        lineChart.animateXY(0,0)
    }

}

