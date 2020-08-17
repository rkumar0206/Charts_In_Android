package com.rohitthebest.chartsinandroid

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.rohitthebest.chartsinandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener, OnChartValueSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var growthList: ArrayList<Growth>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
        growthList = ArrayList()
        populateGrowthList()

        showBarGraph()
        initBarGraph()
    }

    private fun populateGrowthList() {

        growthList?.let {

            it.add(Growth(2000, 10f))
            it.add(Growth(2001, 20f))
            it.add(Growth(2002, 30f))
            it.add(Growth(2003, 40f))
            it.add(Growth(2004, 50f))
            it.add(Growth(2005, 30f))
            it.add(Growth(2006, 60f))
            it.add(Growth(2007, 70f))
            it.add(Growth(2008, 80f))
            it.add(Growth(2009, 100f))
            it.add(Growth(2010, 120f))
            it.add(Growth(2011, 190f))
            it.add(Growth(2012, 130f))
        }

    }

    private fun setClickListeners() {

        binding.barGraphBtn.setOnClickListener(this)
        binding.pieChartBtn.setOnClickListener(this)
        binding.lineChartBtn.setOnClickListener(this)
        binding.bubbleChartBtn.setOnClickListener(this)
        binding.horizontalBarChartBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            binding.barGraphBtn.id -> {

                showBarGraph()

                initBarGraph()
            }

            binding.pieChartBtn.id -> {

                showPieChart()

                initPieGraph()
            }

            binding.lineChartBtn.id -> {

                showLineChart()

                initLineChart()
            }

            binding.bubbleChartBtn.id -> {

                showBubbleChart()

                initBubbleChart()

            }

            binding.horizontalBarChartBtn.id -> {

                showHorizontalBarChart()

                initHorizontalBarChart()
            }

        }
    }


    private fun initBarGraph() {


        val barEntries: ArrayList<BarEntry> = ArrayList()

        growthList?.forEach {

            barEntries.add(BarEntry(it.growthYear.toFloat(), it.growthRate))

        }

        val barDataSet = BarDataSet(barEntries, "Growth")

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 200)

        val barData = BarData(barDataSet)
        barData.barWidth = 0.9f

        binding.barChart.animateY(4000)
        binding.barChart.data = barData
        binding.barChart.setFitBars(true)

        val description = Description()
        description.text = "Growth rate per year"

        binding.barChart.description = description
        binding.barChart.setOnChartValueSelectedListener(this)
        binding.barChart.invalidate()


    }

    private fun initPieGraph() {

        val pieEntries: ArrayList<PieEntry> = ArrayList()

        growthList?.forEach {

            pieEntries.add(PieEntry(it.growthRate, it.growthYear.toString()))

        }

        binding.pieChart.animateXY(4000, 4000)

        val pieDataSet = PieDataSet(pieEntries, "Growth")
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 200)

        val pieData = PieData(pieDataSet)

        binding.pieChart.data = pieData

        val description = Description()
        description.text = "Growth rate per year"

        binding.pieChart.description = description
        binding.pieChart.centerText = "GROWTH"
        //binding.pieChart.holeRadius = 30.0f
        binding.pieChart.setOnChartValueSelectedListener(this)

        binding.pieChart.invalidate()

    }

    private fun initLineChart() {

        val lineEntries: ArrayList<Entry> = ArrayList()

        growthList?.forEach {

            lineEntries.add(Entry(it.growthYear.toFloat(), it.growthRate))
        }

        binding.lineChart.animateXY(4000, 400)

        val lineDataSet = LineDataSet(lineEntries, "Growth")
        lineDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 200)

        val lineData = LineData(lineDataSet)

        binding.lineChart.data = lineData

        val description = Description()
        description.text = "Growth rate per year"

        binding.lineChart.description = description
        binding.lineChart.setOnChartValueSelectedListener(this)
        binding.lineChart.invalidate()
    }

    private fun initBubbleChart() {

        val bubbleEntry: ArrayList<BubbleEntry> = ArrayList()

        growthList?.forEach {

            bubbleEntry.add(BubbleEntry(it.growthYear.toFloat(), it.growthRate, 0.9f))

        }

        binding.bubbleChart.animateXY(4000, 4000)

        val bubbleDataSet = BubbleDataSet(bubbleEntry, "Growth")
        bubbleDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 200)

        val bubbleData = BubbleData(bubbleDataSet)
        binding.bubbleChart.data = bubbleData

        val description = Description()
        description.text = "Growth rate per year"

        binding.bubbleChart.description = description
        binding.bubbleChart.setOnChartValueSelectedListener(this)
        binding.bubbleChart.invalidate()

    }

    private fun initHorizontalBarChart() {

        val hBREntry: ArrayList<BarEntry> = ArrayList()

        growthList?.forEach {

            hBREntry.add(BarEntry(it.growthYear.toFloat(), it.growthRate))
        }

        binding.horizontalBarChart.animateXY(4000, 4000)

        val hBRDataSet = BarDataSet(hBREntry, "Growth")
        hBRDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 200)

        val hbrData = BarData(hBRDataSet)

        binding.horizontalBarChart.data = hbrData

        val description = Description()
        description.text = "Growth rate per year"

        binding.horizontalBarChart.description = description
        binding.horizontalBarChart.setOnChartValueSelectedListener(this)
        binding.horizontalBarChart.invalidate()

    }


    override fun onNothingSelected() {


    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {

        Toast.makeText(this@MainActivity, "${e?.x}, ${e?.y}", Toast.LENGTH_SHORT).show()

    }


    private fun showBarGraph() {

        binding.barChart.visibility = View.VISIBLE
        binding.pieChart.visibility = View.GONE
        binding.lineChart.visibility = View.GONE
        binding.bubbleChart.visibility = View.GONE
        binding.horizontalBarChart.visibility = View.GONE

    }

    private fun showPieChart() {

        binding.barChart.visibility = View.GONE
        binding.pieChart.visibility = View.VISIBLE
        binding.lineChart.visibility = View.GONE
        binding.bubbleChart.visibility = View.GONE
        binding.horizontalBarChart.visibility = View.GONE

    }

    private fun showLineChart() {

        binding.barChart.visibility = View.GONE
        binding.pieChart.visibility = View.GONE
        binding.lineChart.visibility = View.VISIBLE
        binding.bubbleChart.visibility = View.GONE
        binding.horizontalBarChart.visibility = View.GONE


    }

    private fun showBubbleChart() {

        binding.barChart.visibility = View.GONE
        binding.pieChart.visibility = View.GONE
        binding.lineChart.visibility = View.GONE
        binding.bubbleChart.visibility = View.VISIBLE
        binding.horizontalBarChart.visibility = View.GONE

    }


    private fun showHorizontalBarChart() {

        binding.barChart.visibility = View.GONE
        binding.pieChart.visibility = View.GONE
        binding.lineChart.visibility = View.GONE
        binding.bubbleChart.visibility = View.GONE
        binding.horizontalBarChart.visibility = View.VISIBLE

    }


}

