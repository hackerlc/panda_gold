package com.sk.panda.gold.utils

import android.annotation.SuppressLint
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*

/**
 * class info
 * @author joker
 * Email:812405389@qq.com
 * @version 2018/8/17
 */
class GoldTodayYAxisValueFormatter: IAxisValueFormatter {
    @SuppressLint("SimpleDateFormat")
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        val sdf = SimpleDateFormat("HH:mm")
        return sdf.format(Date(value.toLong()*1000))
    }

}